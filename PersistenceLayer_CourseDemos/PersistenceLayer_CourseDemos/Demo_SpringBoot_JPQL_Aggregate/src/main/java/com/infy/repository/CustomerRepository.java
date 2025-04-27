package com.infy.repository;

public interface CustomerRepository {

	public Double getAverageBalance() ;
	public Long getTotalBalance();
	public Long getNumberOfAccounts() ;
	public Integer getMinimumBalance() ;
	public Integer getMaximumBalance() ;
	
}
