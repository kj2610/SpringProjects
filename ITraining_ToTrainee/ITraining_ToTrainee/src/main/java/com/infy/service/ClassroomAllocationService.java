package com.infy.service;

import com.infy.dto.TraineeDTO;
import com.infy.exception.ITrainingException;

public interface ClassroomAllocationService {

	public TraineeDTO getTrainee(Integer traineeId) throws ITrainingException;

	public Integer addTrainee(TraineeDTO trainee) throws ITrainingException;

	public Integer allocateClassroom(Integer traineeId, String classRoomId) throws ITrainingException;

	public String deleteTrainee(Integer traineeId) throws ITrainingException;

}
