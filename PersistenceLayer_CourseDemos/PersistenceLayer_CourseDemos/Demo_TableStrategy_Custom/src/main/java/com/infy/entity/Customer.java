package com.infy.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.TableGenerator;

@Entity
public class Customer{
	@Id
	@TableGenerator(
            name="pkgen", 
            table="id_gen", 
            pkColumnName="gen_key", 
            valueColumnName="gen_value",
            pkColumnValue="cust_id",
            allocationSize=1
            )
	@GeneratedValue(generator="pkgen",strategy=GenerationType.TABLE)
	private Integer customerId;
	private String emailId;
	private String name;
	private LocalDate dateOfBirth;

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

	@Override
	public int hashCode() {
		return 31;
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
		if (this.getCustomerId() == null) {
			if (other.getCustomerId() != null)
				return false;
		}else if (!this.getCustomerId().equals(other.getCustomerId())) {
			return false;
		}
		return true;
	}
	
	
}
