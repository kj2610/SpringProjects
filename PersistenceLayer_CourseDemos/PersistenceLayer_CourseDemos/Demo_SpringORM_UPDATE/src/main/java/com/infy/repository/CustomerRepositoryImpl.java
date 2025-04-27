package com.infy.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.infy.dto.CustomerDTO;
import com.infy.entity.Customer;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public CustomerDTO getCustomer(Integer customerId) {
		CustomerDTO customerDTO = null;
		Customer customer = entityManager.find(Customer.class, customerId);
		if (customer != null) {
			customerDTO = new CustomerDTO();
			customerDTO.setCustomerId(customer.getCustomerId());
			customerDTO.setDateOfBirth(customer.getDateOfBirth());
			customerDTO.setEmailId(customer.getEmailId());
			customerDTO.setName(customer.getName());
			customerDTO.setCustomerType(customer.getCustomerType());
		}
		return customerDTO;
	}

	@Override
	public void addCustomer(CustomerDTO customerDTO) {
		Customer customer = new Customer();
		customer.setCustomerId(customerDTO.getCustomerId());
		customer.setDateOfBirth(customerDTO.getDateOfBirth());
		customer.setEmailId(customerDTO.getEmailId());
		customer.setName(customerDTO.getName());
		customer.setCustomerType(customerDTO.getCustomerType());
		entityManager.persist(customer);
	}

	@Override
	public Integer updateCustomer(Integer customerId, String emailId) {
		Integer customerIdReturned = null;
		Customer customer = entityManager.find(Customer.class, customerId);
		customer.setEmailId(emailId);
		customerIdReturned = customer.getCustomerId();
		return customerIdReturned;
	}
}
