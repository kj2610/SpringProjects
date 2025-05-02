package com.infy.entity;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Classroom {
	@Id
	private String classroomId;
	private String buildingName;
	private Integer capacity;
	private Integer availableCapacity;
	public Classroom() {
		// TODO Auto-generated constructor stub
	}
	public Classroom(String classroomId, String buildingName, Integer capacity, Integer availableCapacity) {
		super();
		this.classroomId = classroomId;
		this.buildingName = buildingName;
		this.capacity = capacity;
		this.availableCapacity = availableCapacity;
	}
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
	public int hashCode() {
		return Objects.hash(availableCapacity, buildingName, capacity, classroomId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Classroom other = (Classroom) obj;
		return Objects.equals(availableCapacity, other.availableCapacity)
				&& Objects.equals(buildingName, other.buildingName) && Objects.equals(capacity, other.capacity)
				&& Objects.equals(classroomId, other.classroomId);
	}
	@Override
	public String toString() {
		return "Classroom [classroomId=" + classroomId + ", buildingName=" + buildingName + ", capacity=" + capacity
				+ ", availableCapacity=" + availableCapacity + "]";
	}
	
	
}
