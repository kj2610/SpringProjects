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
	public List<CustomerDTO> getCustomerdetails() {
		List<CustomerDTO> customerDTOs = null;

		String queryString1 = "SELECT c FROM Customer c where c.customerId = 1001";
		String queryString2 = "SELECT c FROM Customer c where c.city != 'Seattle'";
		String queryString3 = "SELECT c FROM Customer c where c.dateOfBirth > '1990-01-01'";
		String queryString4 = "SELECT c FROM Customer c where c.dateOfBirth >= '1990-01-01'";
		String queryString5 = "SELECT c FROM Customer c where c.dateOfBirth < '1990-01-01'";
		String queryString6 = "SELECT c FROM Customer c where c.dateOfBirth <= '1990-01-01'";
		String queryString7 = "SELECT c FROM Customer c where c.dateOfBirth BETWEEN '1980-01-01' AND '1995-01-01'";
		String queryString8 = "SELECT c FROM Customer c where c.name LIKE 'J%'";
		String queryString9 = "SELECT c FROM Customer c where c.emailId IS NULL";
		String queryString10 = "SELECT c FROM Customer c where c.city IN ('Seattle','Vancouver')";
		Query query = entityManager.createQuery(queryString1);

		List<Customer> result = query.getResultList();

		customerDTOs = new ArrayList<>();

		for (Customer customer : result) {
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
