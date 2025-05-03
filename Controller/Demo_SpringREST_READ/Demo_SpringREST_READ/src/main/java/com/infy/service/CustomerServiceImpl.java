package com.infy.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.exception.InfyBankException;
import com.infy.repository.CustomerRepository;
import com.infy.entity.Customer;

import com.infy.dto.CustomerDTO;

@Service(value = "customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CustomerDTO getCustomer(Integer customerId) throws InfyBankException {
		Optional<Customer> optional = customerRepository.findById(customerId);
		Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		return modelMapper.map(customer, CustomerDTO.class);
	}

	@Override
	public List<CustomerDTO> getAllCustomers() throws InfyBankException {
		List<Customer> customers = (List<Customer>)customerRepository.findAll();
		if (customers.isEmpty())
			throw new InfyBankException("Service.CUSTOMERS_NOT_FOUND");
		return modelMapper.map(customers, new TypeToken<List<CustomerDTO>>() {
		}.getType());
	}

}
