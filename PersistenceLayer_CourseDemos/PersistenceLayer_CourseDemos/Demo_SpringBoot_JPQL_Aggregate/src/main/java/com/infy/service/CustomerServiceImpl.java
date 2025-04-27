package com.infy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.exception.InfyBankException;
import com.infy.repository.CustomerRepository;

@Service(value = "customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Double getAverageBalance() throws InfyBankException {
		return customerRepository.getAverageBalance();
	}

	@Override
	public Long getTotalBalance() throws InfyBankException {
		return customerRepository.getTotalBalance();
	}

	@Override
	public Long getNumberOfAccounts() throws InfyBankException {
		return customerRepository.getNumberOfAccounts();
	}

	@Override
	public Integer getMinimumBalance() throws InfyBankException {
		return customerRepository.getMinimumBalance();
	}

	@Override
	public Integer getMaximumBalance() throws InfyBankException {
		return customerRepository.getMaximumBalance();
	}
}
