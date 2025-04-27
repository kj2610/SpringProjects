package com.infy.dto;

import java.time.LocalDate;

public class AccountDTO {
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
	public String toString() {
		return "AccountDTO [accountNumber=" + accountNumber + ", accountType=" + accountType + ", openingDate="
				+ openingDate + ", balance=" + balance + ", accountStatus=" + accountStatus + "]";
	}

}
