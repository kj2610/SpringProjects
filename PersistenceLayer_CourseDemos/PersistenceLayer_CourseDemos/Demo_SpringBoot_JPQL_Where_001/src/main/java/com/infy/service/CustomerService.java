package com.infy.service;

import java.util.List;

import com.infy.dto.CustomerDTO;
import com.infy.exception.InfyBankException;

public interface CustomerService {

	public List<CustomerDTO> getCustomerdetails(Integer customerId) throws InfyBankException;	
	
}
