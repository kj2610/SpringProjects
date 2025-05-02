package com.infy.dto;


public class TraineeDTO {

	private Integer traineeId;
	private String traineeName;
	private ClassroomDTO classroom;
	
	public String getTraineeName() {
		return traineeName;
	}
	public void setTraineeName(String traineeName) {
		this.traineeName = traineeName;
	}
	public Integer getTraineeId() {
		return traineeId;
	}
	public void setTraineeId(Integer traineeId) {
		this.traineeId = traineeId;
	}
	
	public ClassroomDTO getClassroom() {
		return classroom;
	}
	public void setClassroom(ClassroomDTO classroom) {
		this.classroom = classroom;
	}
	
	@Override
	public String toString() {
		return "TraineeDTO [traineeId=" + traineeId + ", traineeName=" + traineeName + ", classroom=" + classroom + "]";
	}

	
}
