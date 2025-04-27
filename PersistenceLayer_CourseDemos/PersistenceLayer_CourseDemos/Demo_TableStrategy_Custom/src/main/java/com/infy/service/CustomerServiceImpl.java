package com.infy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dto.CustomerDTO;
import com.infy.entity.Customer;
import com.infy.repository.CustomerRepository;

@Service(value="customerService")
@Transactional(readOnly=true)
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRespository;

	@Transactional(readOnly=false,propagation=Propagation.REQUIRES_NEW)
	public Integer addCustomer(CustomerDTO customerDTO) {

		Customer customer=new Customer();
		customer.setDateOfBirth(customerDTO.getDateOfBirth());
		customer.setEmailId(customerDTO.getEmailId());
		customer.setName(customerDTO.getName());

		customerRespository.save(customer);
		return customer.getCustomerId();

	}

}
