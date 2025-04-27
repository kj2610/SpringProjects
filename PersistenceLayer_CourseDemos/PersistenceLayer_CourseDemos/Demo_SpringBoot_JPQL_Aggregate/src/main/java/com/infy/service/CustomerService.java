package com.infy.service;

import com.infy.exception.InfyBankException;

public interface CustomerService {
	public Double getAverageBalance() throws InfyBankException;
	public Long getTotalBalance() throws InfyBankException;
	public Long getNumberOfAccounts() throws InfyBankException;
	public Integer getMinimumBalance() throws InfyBankException;
	public Integer getMaximumBalance() throws InfyBankException;	
}
