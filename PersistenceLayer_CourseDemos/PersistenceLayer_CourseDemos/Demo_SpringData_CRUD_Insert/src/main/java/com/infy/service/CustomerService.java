package com.infy.service;

import com.infy.dto.CustomerDTO;
import com.infy.exception.InfyBankException;

public interface CustomerService {
	public void addCustomer(CustomerDTO customer) throws InfyBankException;
}
