package com.infy.entity;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	@Id
	private Integer customerId;
	private String emailId;
	private String name;
	private LocalDate dateOfBirth;
	public Customer() {
		
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Customer(Integer customerId, String emailId, String name, LocalDate dateOfBirth) {
		super();
		this.customerId = customerId;
		this.emailId = emailId;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}
	@Override
	public int hashCode() {
		return Objects.hash(customerId, dateOfBirth, emailId, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(customerId, other.customerId) && Objects.equals(dateOfBirth, other.dateOfBirth)
				&& Objects.equals(emailId, other.emailId) && Objects.equals(name, other.name);
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", emailId=" + emailId + ", name=" + name + ", dateOfBirth="
				+ dateOfBirth + "]";
	}
	
	
}
