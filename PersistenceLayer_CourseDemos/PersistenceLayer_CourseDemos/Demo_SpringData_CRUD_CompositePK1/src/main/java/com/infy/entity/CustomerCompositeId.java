package com.infy.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CustomerCompositeId implements Serializable{
	
	private Integer customerId;
	private String emailId;
	
	//default constructor
	//parameterized constructor
	//getters and setters


	public CustomerCompositeId() {
	}

	public CustomerCompositeId(Integer customerId, String emailId) {
		super();
		this.customerId = customerId;
		this.emailId = emailId;
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
	
}
