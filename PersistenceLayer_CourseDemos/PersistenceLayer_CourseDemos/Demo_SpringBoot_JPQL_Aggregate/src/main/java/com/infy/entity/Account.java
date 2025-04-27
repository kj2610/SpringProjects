package com.infy.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Account {

	@Id
	private Integer accountNumber;
	private String accountType;
	private LocalDate openingDate;
	private Integer balance;
	private String accountStatus;

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public LocalDate getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(LocalDate openingDate) {
		this.openingDate = openingDate;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getAccountNumber() == null) ? 0 : this.getAccountNumber().hashCode());
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
		Account other = (Account) obj;
		if (this.getAccountNumber() == null) {
			if (other.getAccountNumber() != null)
				return false;
		} 
		else if (!this.getAccountNumber().equals(other.getAccountNumber()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", accountType=" + accountType + ", openingDate="
				+ openingDate + ", balance=" + balance + ", accountStatus=" + accountStatus + "]";
	}

}
