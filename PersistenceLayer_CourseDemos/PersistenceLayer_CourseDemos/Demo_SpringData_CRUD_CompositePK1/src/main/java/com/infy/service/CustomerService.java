package com.infy.service;

import java.util.List;

import com.infy.dto.CustomerDTO;
import com.infy.entity.CustomerCompositeId;
import com.infy.exception.InfyBankException;

public interface CustomerService {
	Integer addCustomer(CustomerDTO customer) throws InfyBankException;
	CustomerDTO getCustomer(CustomerCompositeId customerCompositeId) throws InfyBankException;
	List<CustomerDTO> findAll() throws InfyBankException;
	void updateCustomer(CustomerCompositeId customerCompositeId, String name) throws InfyBankException;
	void deleteCustomer(CustomerCompositeId customerCompositeId) throws InfyBankException;
}
