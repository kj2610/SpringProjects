package com.infy.entity;

import java.time.LocalDate;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Customer {

	@EmbeddedId
	private CustomerCompositeId customerCompositeId;
	private String name;
	private LocalDate dateOfBirth;

	public CustomerCompositeId getCustomerCompositeId() {
		return customerCompositeId;
	}

	public void setCustomerCompositeId(CustomerCompositeId customerCompositeId) {
		this.customerCompositeId = customerCompositeId;
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerCompositeId == null) ? 0 : customerCompositeId.hashCode());
		result = prime * result + ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
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
		if (customerCompositeId == null) {
			if (other.customerCompositeId != null)
				return false;
		} else if (!customerCompositeId.equals(other.customerCompositeId))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
