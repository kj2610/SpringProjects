package com.infy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.dto.CustomerDTO;
import com.infy.entity.Customer;
import com.infy.exception.InfyBankException;
import com.infy.repository.CustomerRepository;


@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public void addCustomer(CustomerDTO customerDTO) throws InfyBankException {
		// TODO Auto-generated method stub
		Optional<Customer> optional = customerRepository.findById(customerDTO.getCustomerDTO());
		if(optional.isPresent()) {
			throw new InfyBankException("Service.CUSTOMER_FOUND");
		}
		Customer customer = new Customer();
		customer.setDateOfBirth(customerDTO.getDateofBirth());
		customer.setEmailId(customerDTO.getEmaildId());
		customer.setName(customerDTO.getName());
		customer.setCustomerId(customerDTO.getCustomerDTO());
		customerRepository.save(customer);
	}

	@Override
	public void updateCustomer(Integer customerId, String emailId) throws InfyBankException {
		// TODO Auto-generated method stub
		
		Optional<Customer>optional = customerRepository.findById(customerId);
		Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		customer.setEmailId(emailId);
		customerRepository.save(customer);
		
	}

	@Override
	public CustomerDTO getCustomer(Integer customerId) throws InfyBankException {
		// TODO Auto-generated method stub
		Optional<Customer> optional = customerRepository.findById(customerId);
		Customer customer = optional.orElseThrow(() -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerDTO(customerId);
		customerDTO.setName(customer.getName());
		customerDTO.setDateofBirth(customer.getDateOfBirth());
		customerDTO.setEmaildId(customer.getEmailId());
		return customerDTO;
	}

	@Override
	public List<CustomerDTO> getAllCustomer() {
		// TODO Auto-generated method stub
		Iterable<Customer> customers = customerRepository.findAll();
		List<CustomerDTO> customerDTOs = new ArrayList<>();
		customers.forEach(customer -> {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCustomerDTO(customer.getCustomerId());
			customerDTO.setDateofBirth(customer.getDateOfBirth());
			customerDTO.setEmaildId(customer.getEmailId());
			customerDTO.setName(customer.getName());
			customerDTOs.add(customerDTO);
		});
		
		return customerDTOs;
	}

}
