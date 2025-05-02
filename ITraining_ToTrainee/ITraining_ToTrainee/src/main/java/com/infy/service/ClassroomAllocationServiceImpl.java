package com.infy.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dto.ClassroomDTO;
import com.infy.dto.TraineeDTO;
import com.infy.entity.Classroom;
import com.infy.entity.Trainee;
import com.infy.exception.ITrainingException;
import com.infy.repository.ClassroomRepository;
import com.infy.repository.TraineeRepository;

@Service
public class ClassroomAllocationServiceImpl implements ClassroomAllocationService {
	
	@Autowired
	private ClassroomRepository classroomRepository;
	
	@Autowired
	private TraineeRepository traineeRepository;
	@Autowired
	ModelMapper mapper;
	
	/**
	  this method  calls getTrainee method of ClassroomAllocationDAOImpl class.
	 
	  @param -  Integer traineeId 
	  @return - Trainee object returned from getTrainee method of ClassroomAllocationDAOImpl class
	  @throws "Service.TRAINEE_NOT_FOUND" exception If trainee object returned from getTrainee method of ClassroomAllocationDAOImpl class is null
	 */
	public TraineeDTO getTrainee(Integer traineeId) throws ITrainingException {
		Optional<Trainee> optional = traineeRepository.findById(traineeId);
		Trainee trainee = optional.orElseThrow(() -> new ITrainingException("Service.TRAINEE_NOT_FOUND"));
		Classroom classroom = trainee.getClassroom();
		ClassroomDTO classroomDTO = null;
		if(classroom != null) {
			classroomDTO = mapper.map(classroom, ClassroomDTO.class);
		}
		
		TraineeDTO traineeDTO = mapper.map(trainee, TraineeDTO.class);
		traineeDTO.setClassroom(classroomDTO);
		return traineeDTO;
	}

	/**
	  this method will call the addTrainee method of ClassroomAllocationDAOImpl class. 
	  @param - Trainee object
	  @return - Integer traineeId returned from addTrainee method of ClassroomAllocationDAOImpl class. 
	 */
	public Integer addTrainee(TraineeDTO traineeDTO) throws ITrainingException {
		Trainee trainee = mapper.map(traineeDTO, Trainee.class);
		traineeRepository.save(trainee);
		return trainee.getTraineeId();
	}

	/**
	  this method will call allocateClassroom method of ClassroomAllocationDAOImpl class, which in turn will return value
	  @param -  Integer traineeId, String classRoomId
	  @return - Integer value returned from llocateClassroom method of ClassroomAllocationDAOImpl class
	  @throws -exception as below if the return from llocateClassroom method of ClassroomAllocationDAOImpl class is
	    0 - throw "Service.TRAINEE_NOT_FOUND" exception
	    -1 - throw "Service.CLASSROOM_NOT_FOUND" exception
	    -2 - throw "Service.CLASSROOM_FULL" exception.
	 */
	public Integer allocateClassroom(Integer traineeId, String classRoomId) throws ITrainingException {
		Optional<Trainee> optional = traineeRepository.findById(traineeId);
		Trainee trainee = optional.orElseThrow(() -> new ITrainingException("Service.TRAINEE_NOT_FOUND"));
		Optional<Classroom> optional2 = classroomRepository.findById(classRoomId);
		Classroom classroom = optional2.orElseThrow(() -> new ITrainingException("Service.CLASSROOM_NOT_FOUND"));
		Integer availablCapacity = classroom.getAvailableCapacity();
		if(availablCapacity <= 0) {
			throw new ITrainingException("Service.CLASSROOM_FULL");
		}
		trainee.setClassroom(classroom);
		classroom.setAvailableCapacity(availablCapacity - 1);
		classroomRepository.save(classroom);
		traineeRepository.save(trainee);
		return 1;
	}
	/**
	  this method will call deleteTrainee method of ClassroomAllocationDAOImpl class which will return a string value.
	  @param -  Integer traineeId
	  @return - String   value that was received from deleteTrainee method of ClassroomAllocationDAOImpl class
	  @throws -Service.TRAINEE_NOT_FOUND exception if value returned by deleteTrainee method of ClassroomAllocationDAOImpl class is null
	 */
	public String deleteTrainee(Integer traineeId) throws ITrainingException {
		Optional<Trainee> optional = traineeRepository.findById(traineeId);
		Trainee trainee = optional.orElseThrow(() -> new ITrainingException("Service.TRAINEE_NOT_FOUND"));
		Classroom classroom = trainee.getClassroom();
		if(classroom != null) {
			classroom.setAvailableCapacity(classroom.getAvailableCapacity() + 1);
			classroomRepository.save(classroom);
		}
		String nameString = trainee.getTraineeName();
		traineeRepository.delete(trainee);
		return nameString;
	}

}
