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

	public List<CustomerDTO> getCustomerdetails() {
		List<CustomerDTO>customerDTOs = new ArrayList<>();
		String queryString = "select c from Customer c";
		Query query = entityManager.createQuery(queryString);
		List<Customer>customers = query.getResultList();
		customers.forEach(c ->{
			CustomerDTO customerDTO = new CustomerDTO();
			customerDTO.setCustomerId(c.getCustomerId());
			customerDTO.setName(c.getName());
			customerDTO.setEmailId(c.getEmailId());
			customerDTO.setDateOfBirth(c.getDateOfBirth());
			customerDTO.setCity(c.getCity());
			customerDTOs.add(customerDTO);
		});
		
		return customerDTOs;
//		List<CustomerDTO> customerDTOs = null;
//
//		String queryString = "select c from Customer c";
//
//		Query query = entityManager.createQuery(queryString);
//
//		List<Customer> customers = query.getResultList();
//
//		customerDTOs = new ArrayList<>();
//
//		for (Customer customerEntity : customers) {
//			CustomerDTO customerDTO = new CustomerDTO();
//			customerDTO.setCustomerId(customerEntity.getCustomerId());
//			customerDTO.setDateOfBirth(customerEntity.getDateOfBirth());
//			customerDTO.setEmailId(customerEntity.getEmailId());
//			customerDTO.setName(customerEntity.getName());
//			customerDTO.setCity(customerEntity.getCity());
//			customerDTOs.add(customerDTO);
//		}
//		return customerDTOs;
	}

	public List<Object[]> getCustomerNameAndDob() {
		String queryString = "select c.name, c.dateOfBirth from Customer c";
		Query query = entityManager.createQuery(queryString);
		List<Object[]> result = query.getResultList();
		return result;
//		String queryString = "select c.name,c.dateOfBirth from Customer c";
//
//		Query query = entityManager.createQuery(queryString);
//
//		List<Object[]> result = query.getResultList();
//
//		return result;
	}

	public List<String> getCustomerName() {
		List<String>customerNames = null;
		String queryString = "select c.name from Customer c";
		Query query = entityManager.createQuery(queryString);
		customerNames = query.getResultList();
		return customerNames;
//		List<String> customerNames = null;
//
//		String queryString = "select c.name from Customer c";
//
//		Query query = entityManager.createQuery(queryString);
//
//		customerNames = query.getResultList();
//
//		return customerNames;
	}

}
