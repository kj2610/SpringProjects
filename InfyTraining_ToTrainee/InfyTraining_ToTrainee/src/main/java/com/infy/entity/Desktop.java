package com.infy.entity;

import java.util.Objects;

import com.infy.dto.DesktopStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class Desktop {
	@Id
	private String desktopId;
	@Column(name = "desktopMake")
	private String desktopName;
	private String desktopModel;
	@Enumerated(EnumType.STRING)
	private DesktopStatus desktopStatus;
	
	public Desktop() {
		
	}
	
	public Desktop(String desktopId, String desktopName, String desktopModel, DesktopStatus desktopStatus) {
		super();
		this.desktopId = desktopId;
		this.desktopName = desktopName;
		this.desktopModel = desktopModel;
		this.desktopStatus = desktopStatus;
	}

	public String getDesktopId() {
		return desktopId;
	}

	public void setDesktopId(String desktopId) {
		this.desktopId = desktopId;
	}

	public String getDesktopName() {
		return desktopName;
	}

	public void setDesktopName(String desktopName) {
		this.desktopName = desktopName;
	}

	public String getDesktopModel() {
		return desktopModel;
	}

	public void setDesktopModel(String desktopModel) {
		this.desktopModel = desktopModel;
	}

	public DesktopStatus getDesktopStatus() {
		return desktopStatus;
	}

	public void setDesktopStatus(DesktopStatus desktopStatus) {
		this.desktopStatus = desktopStatus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(desktopId, desktopModel, desktopName, desktopStatus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Desktop other = (Desktop) obj;
		return Objects.equals(desktopId, other.desktopId) && Objects.equals(desktopModel, other.desktopModel)
				&& Objects.equals(desktopName, other.desktopName) && desktopStatus == other.desktopStatus;
	}

	@Override
	public String toString() {
		return "Desktop [desktopId=" + desktopId + ", desktopName=" + desktopName + ", desktopModel=" + desktopModel
				+ ", desktopStatus=" + desktopStatus + "]";
	}
	
	
	
	
		
}
