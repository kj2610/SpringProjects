package com.infy.dto;

public class ClassroomDTO {

	private String classroomId;
	private String buildingName;
	private Integer capacity;
	private Integer availableCapacity;

	public String getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(String classroomId) {
		this.classroomId = classroomId;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getAvailableCapacity() {
		return availableCapacity;
	}

	public void setAvailableCapacity(Integer availableCapacity) {
		this.availableCapacity = availableCapacity;
	}

	@Override
	public String toString() {
		return "ClassroomDTO [classroomId=" + classroomId + ", buildingName=" + buildingName + ", capacity=" + capacity
				+ ", availableCapacity=" + availableCapacity + "]";
	}
	
	
}
