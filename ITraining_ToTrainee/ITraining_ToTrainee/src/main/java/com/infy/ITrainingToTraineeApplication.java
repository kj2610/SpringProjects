package com.infy;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.infy.dto.TraineeDTO;
import com.infy.service.ClassroomAllocationService;

@SpringBootApplication
public class ITrainingToTraineeApplication implements CommandLineRunner {
	
	public static final Logger LOGGER = LogManager.getLogger(ITrainingToTraineeApplication.class);
	
	@Autowired
	ClassroomAllocationService allocationService;
	@Autowired
	Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(ITrainingToTraineeApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		getTrainee();
//		addTrainee();
		allocateClassroom();
		deleteTraineeDetails();
	}

	public void getTrainee() {
		try {
			Integer traineeId = 800002;
			TraineeDTO traineeDTO = allocationService.getTrainee(traineeId);
			LOGGER.info(traineeDTO);
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");
			LOGGER.info( message);
		}
	}

	public void addTrainee() {
		try {
			TraineeDTO traineeDTO = new TraineeDTO();
			traineeDTO.setTraineeName("John");
			

			Integer traineeId = allocationService.addTrainee(traineeDTO);

			LOGGER.info(environment.getProperty("UserInterface.TRAINEE_ADDED_SUCCESS") + " : " + traineeId);
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");
			LOGGER.info( message);
		}

	}

	public void allocateClassroom() {
		try {

			String classroomId = "L1-72";
			Integer traineeId = 800003;
			allocationService.allocateClassroom(traineeId, classroomId);

			LOGGER.info(environment.getProperty("UserInterface.CLASSROOM_ALLOCATED_SUCCESS") + " : " + traineeId);
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}
	}

	public void deleteTraineeDetails() {
		try {
			Integer traineeId = 800001;

			String name = allocationService.deleteTrainee(traineeId);

			LOGGER.info(name + " " + environment.getProperty("UserInterface.TRAINEE_DELETED_SUCCESS"));
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),"Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}
	}

}

