package com.infy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dto.CustomerDTO;
import com.infy.entity.Customer;
import com.infy.entity.CustomerCompositeId;
import com.infy.exception.InfyBankException;
import com.infy.repository.CustomerRepository;

@Service(value="customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Integer addCustomer(CustomerDTO customerDTO) throws InfyBankException {
		Customer customer = new Customer();
		CustomerCompositeId customerCompositeId = new CustomerCompositeId(customerDTO.getCustomerId(), customerDTO.getEmailId());
		customer.setCustomerCompositeId(customerCompositeId);
		customer.setDateOfBirth(customerDTO.getDateOfBirth());
		customer.setName(customerDTO.getName());
		Customer customerAfterSave = customerRepository.save(customer);
		return customerAfterSave.getCustomerCompositeId().getCustomerId();
	}

	@Override
	public CustomerDTO getCustomer(CustomerCompositeId customerCompositeId) throws InfyBankException {
		Optional<Customer> optCustomer = customerRepository.findById(customerCompositeId);
		Customer customer = optCustomer.orElseThrow( () -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(customer.getCustomerCompositeId().getCustomerId());
		customerDTO.setDateOfBirth(customer.getDateOfBirth());
		customerDTO.setEmailId(customer.getCustomerCompositeId().getEmailId());
		customerDTO.setName(customer.getName());
		
		return customerDTO;
	}

	@Override
	public List<CustomerDTO> findAll() throws InfyBankException {
		Iterable<Customer> iterables = customerRepository.findAll();
		
		List<CustomerDTO> customerDTOs = new ArrayList<>();
		iterables.forEach(customer -> {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCustomerId(customer.getCustomerCompositeId().getCustomerId());
			customerDTO.setDateOfBirth(customer.getDateOfBirth());
			customerDTO.setEmailId(customer.getCustomerCompositeId().getEmailId());
			customerDTO.setName(customer.getName());
			
			customerDTOs.add(customerDTO);
		});
		
		if (customerDTOs.isEmpty()) {
			throw new InfyBankException("Service.CUSTOMERS_NOT_FOUND");
		}
		return customerDTOs;
	}

	@Override
	public void updateCustomer(CustomerCompositeId customerCompositeId, String name) throws InfyBankException {
		Optional<Customer> optCustomer = customerRepository.findById(customerCompositeId);
		Customer customer = optCustomer.orElseThrow( () -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		customer.setName(name);
	}
	
	@Override
	public void deleteCustomer(CustomerCompositeId customerCompositeId) throws InfyBankException {
		Optional<Customer> optCustomer = customerRepository.findById(customerCompositeId);
		Customer customer = optCustomer.orElseThrow( () -> new InfyBankException("Service.CUSTOMER_NOT_FOUND"));
		customerRepository.delete(customer);
	}
}
