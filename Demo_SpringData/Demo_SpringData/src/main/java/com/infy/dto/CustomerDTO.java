package com.infy.dto;

import java.time.LocalDate;

public class CustomerDTO {
	private Integer customerDTO;
	private String emaildId;
	private String name;
	private LocalDate dateofBirth;
	public Integer getCustomerDTO() {
		return customerDTO;
	}
	public void setCustomerDTO(Integer customerDTO) {
		this.customerDTO = customerDTO;
	}
	public String getEmaildId() {
		return emaildId;
	}
	public void setEmaildId(String emaildId) {
		this.emaildId = emaildId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDateofBirth() {
		return dateofBirth;
	}
	public void setDateofBirth(LocalDate dateofBirth) {
		this.dateofBirth = dateofBirth;
	}
	@Override
	public String toString() {
		return "CustomerDTO [customerDTO=" + customerDTO + ", emaildId=" + emaildId + ", name=" + name
				+ ", dateofBirth=" + dateofBirth + "]";
	}
	
	

}
