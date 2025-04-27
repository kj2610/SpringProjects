package com.infy.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.infy.model.CustomerType;

@Entity
@Table(name="customer")
public class CustomerEntity {

	@Id
	
	private Integer customerId;
	@Column(name="emailid")
	private String emailId;
	private String name;
	
	private LocalDate dateOfBirth;
	@Enumerated(value=EnumType.STRING)
	private CustomerType customerType;
	
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

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}
}
