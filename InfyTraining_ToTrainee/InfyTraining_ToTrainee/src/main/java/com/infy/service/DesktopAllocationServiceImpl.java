package com.infy.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dto.DesktopDTO;
import com.infy.dto.DesktopStatus;
import com.infy.dto.TraineeDTO;
import com.infy.entity.Desktop;
import com.infy.entity.Trainee;
import com.infy.exception.InfyTrainingException;
import com.infy.repository.DesktopRepository;
import com.infy.repository.TraineeRespository;


@Service(value = "desktopAllocationService")
@Transactional
public class DesktopAllocationServiceImpl implements DesktopAllocationService {
	
	@Autowired
	private  TraineeRespository traineeRespository;
	
	@Autowired
	private DesktopRepository desktopRepository;
	
	@Autowired
	private ModelMapper mapper;

	/**
	 This method calls findById method of traineeRespository sending  traineeId received in parameter
	 @param Integer traineeId
	 @return TraineeDTO object object populated from entity object returned by findById method of traineeRespository
	 @throws Service.TRAINEE_NOT_FOUND exception if object returned by findById method of traineeRespository is null
	 */
	@Override
	public TraineeDTO getTraineeDetails(Integer traineeId) throws InfyTrainingException {
		Optional<Trainee> optional = traineeRespository.findById(traineeId);
		Trainee trainee = optional.orElseThrow(() -> new InfyTrainingException("Service.TRAINEE_NOT_FOUND"));
		Desktop desktop = trainee.getDesktop(); 
		DesktopDTO desktopDTO = null;
		if(desktop != null) {
			Optional<Desktop> desk  = desktopRepository.findById(desktop.getDesktopId());
			if(desk.isPresent()) {
				desktopDTO = mapper.map(desk, DesktopDTO.class);
			}
		}
		TraineeDTO traineeDTO = mapper.map(trainee, TraineeDTO.class);
		traineeDTO.setDesktop(desktopDTO);	
		return traineeDTO;
	}
	
	/**
	 This method calls findById method of desktopRepository sending  desktopId received in parameter
	 @param String despktopId
	 @return DesktopDTO object populated from entity object returned by findById method of desktopRepository
	 @throws Service.DESKTOP_NOT_FOUND exception if object returned by findById method of desktopRepository is null
	 */
	@Override
	public DesktopDTO getDesktopDetails(String desktopId) throws InfyTrainingException {
		Optional<Desktop> optional = desktopRepository.findById(desktopId);
		Desktop desktop = optional.orElseThrow(() -> new InfyTrainingException("Service.DESKTOP_NOT_FOUND"));
		DesktopDTO desktopDTO = mapper.map(desktop, DesktopDTO.class);
		
		return desktopDTO;
	}

	/**
	 This method calls save method of traineeRespository sending Trainee entity object populated by trainee object received in parameter
	 @param TraineeDTO object
	 @return Integer traineeId after calling save method of traineeRespository
	 
	 */
	@Override
	@Modifying
	public Integer addTrainee(TraineeDTO traineeDTO) throws InfyTrainingException {
		Trainee trainee = mapper.map(traineeDTO, Trainee.class);
		traineeRespository.save(trainee);
		return trainee.getTraineeId();

	}

	/**
	 This method calls findById method of traineeRespository sending  traineeId received in parameter, then 
	 checks whether the trainee is allocated to with a desktop, then calls
	 findById method of traineeRespository sending  desktopId received in parameter, then
	 checks whether the desktop is allocated to a trainee and then it allocates the desktop to the trainee.
	 @param Integer traineeId, String desktopId
	 @throws 
	 -Service.TRAINEE_NOT_FOUND exception if object returned by findById method of traineeRespository is null
	 -Service.TRAINEE_DESKTOP_FOUND exception if desktop is already allocated to this trainee
	 -Service.DESKTOP_NOT_FOUND exception if object returned by findById method of desktopRepository is null
	 -Service.DESKTOP_ALREADY_ALLOCATED exception if desktop is already allocated to some other trainee
	 */
	@Override
	public void allocateDesktop(Integer traineeId, String desktopId) throws InfyTrainingException {
		Optional<Trainee> optional = traineeRespository.findById(traineeId);
		Trainee trainee = optional.orElseThrow(() -> new InfyTrainingException("Service.TRAINEE_NOT_FOUND"));
		Desktop desktop = trainee.getDesktop();
		if(desktop != null) {
			throw new InfyTrainingException("Service.TRAINEE_DESKTOP_FOUND");
		}
		Optional<Desktop> deOptional = desktopRepository.findById(desktopId);
		Desktop desktop2 = deOptional.orElseThrow(() -> new InfyTrainingException("Service.DESKTOP_NOT_FOUND"));
		DesktopStatus desktopStatus = desktop2.getDesktopStatus();
		if(desktopStatus == DesktopStatus.ALLOCATED) {
			throw new InfyTrainingException("Service.DESKTOP_ALREADY_ALLOCATED");
		}
		desktop2.setDesktopStatus(DesktopStatus.ALLOCATED);
		desktopRepository.save(desktop2);
		trainee.setDesktop(desktop2);
		traineeRespository.save(trainee);
	}
	/**
	 This method calls findById method of traineeRespository sending  traineeId received in parameter, then
	 it deallocates the desktop allocated to the trainee.
	 @param Integer traineeId
	 @throws
	 - Service.TRAINEE_NOT_FOUND exception if object returned by findById method of traineeRespository is null
	 - Service.DESKTOP_NOT_ALLOCATED exception if no desktop is allocated to the trainee
	 */
	@Override
	@Modifying
	public void deallocateDesktop(Integer traineeId) throws InfyTrainingException {
		Optional<Trainee> optional = traineeRespository.findById(traineeId);
		Trainee trainee = optional.orElseThrow(() -> new InfyTrainingException("Service.TRAINEE_NOT_FOUND"));
		Desktop desktop = trainee.getDesktop();
		if(desktop == null) {
			throw new InfyTrainingException("Service.DESKTOP_NOT_ALLOCATED");
		}
		trainee.setDesktop(null);
		desktop.setDesktopStatus(DesktopStatus.AVAILABLE);
		desktopRepository.save(desktop);
		traineeRespository.save(trainee);
		
	}
	/**
	 This method first calls findById method of traineeRespository sending  traineeId received in parameter,
	 then calls delete method of traineeRespository sending traineeId received in parameter
	 @param Integer traineeId
	 @throws Service.TRAINEE_NOT_FOUND exception if Trainee object returned by findById method of traineeRespository is null 
	 */
	@Override
	@Modifying
	public void deleteTrainee(Integer traineeId) throws InfyTrainingException {
		Optional<Trainee>optional = traineeRespository.findById(traineeId);
		Trainee trainee = optional.orElseThrow(() -> new InfyTrainingException("Service.TRAINEE_NOT_FOUND"));
		traineeRespository.delete(trainee);
	}
	
}
