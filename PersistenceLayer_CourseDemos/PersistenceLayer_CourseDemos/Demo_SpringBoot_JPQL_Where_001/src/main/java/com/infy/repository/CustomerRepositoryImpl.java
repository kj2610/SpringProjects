package com.infy.repository;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import org.springframework.stereotype.Repository;

import com.infy.dto.CustomerDTO;
import com.infy.entity.Customer;

@Repository(value = "customerRepository")
public class CustomerRepositoryImpl implements CustomerRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<CustomerDTO> getCustomerdetails(Integer customerId) {
		List<CustomerDTO> customerDTOs = null;
//		String queryString = "select c from Customer c where c.customerId=?1";
//		Query query = entityManager.createQuery(queryString);
//		query.setParameter(1,customerId);
//		List<Customer> customers = query.getResultList();
		
		String queryString = "select c from Customer c where c.customerId=:customerId";
		Query query = entityManager.createQuery(queryString);
		query.setParameter("customerId",customerId);
		List<Customer>customers = query.getResultList();
		
		// Comment the below 3 lines while using named parameter
		
//		String queryString = "select c from Customer c where c.customerId=?1";
//		Query query = entityManager.createQuery(queryString);
//		query.setParameter(1, customerId);

		// Uncomment the below code for using named parameter
		/*
		 * String queryString
		 * ="select c from CustomerEntity c where c.customerId=:customerId"; Query
		 * query=entityManager.createQuery(queryString);
		 * query.setParameter("customerId", customerId);
		 */
		customerDTOs = new ArrayList<>();

		for (Customer customer : customers) {
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCustomerId(customer.getCustomerId());
			customerDTO.setDateOfBirth(customer.getDateOfBirth());
			customerDTO.setEmailId(customer.getEmailId());
			customerDTO.setName(customer.getName());
			customerDTO.setCity(customer.getCity());
			customerDTOs.add(customerDTO);
		}
		return customerDTOs;
	}

}
