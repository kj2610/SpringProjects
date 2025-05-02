package com.infy.entity;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Trainee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer traineeId;
	private String traineeName;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "desktop_id")
	private Desktop desktop;
	
	public Trainee() {
		
	}

	public Trainee(Integer traineeId, String traineeName, Desktop desktop) {
		super();
		this.traineeId = traineeId;
		this.traineeName = traineeName;
		this.desktop = desktop;
	}

	public Integer getTraineeId() {
		return traineeId;
	}

	public void setTraineeId(Integer traineeId) {
		this.traineeId = traineeId;
	}

	public String getTraineeName() {
		return traineeName;
	}

	public void setTraineeName(String traineeName) {
		this.traineeName = traineeName;
	}

	public Desktop getDesktop() {
		return desktop;
	}

	public void setDesktop(Desktop desktop) {
		this.desktop = desktop;
	}

	@Override
	public int hashCode() {
		return Objects.hash(desktop, traineeId, traineeName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trainee other = (Trainee) obj;
		return Objects.equals(desktop, other.desktop) && Objects.equals(traineeId, other.traineeId)
				&& Objects.equals(traineeName, other.traineeName);
	}

	@Override
	public String toString() {
		return "Trainee [traineeId=" + traineeId + ", traineeName=" + traineeName + ", desktop=" + desktop + "]";
	}
	
	
	
}
