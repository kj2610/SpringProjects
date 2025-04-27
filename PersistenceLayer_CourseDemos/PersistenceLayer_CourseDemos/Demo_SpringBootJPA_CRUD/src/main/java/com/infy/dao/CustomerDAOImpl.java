package com.infy.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import com.infy.entity.CustomerEntity;
import com.infy.model.Customer;

@Repository(value = "customerDAO")
public class CustomerDAOImpl implements CustomerDAO {

	@PersistenceContext
	private EntityManager entityManager;
	/**
	  This method will add details of received customer to the database.
	 
	  @param  customer object contains details of customer 
	  @return -  Integer customerId of customer persisted to the database
	 */
	public Integer addCustomer(Customer customer) {

		Integer customerId = null;

		CustomerEntity entity = new CustomerEntity();
		entity.setCustomerId(customer.getCustomerId());
		entity.setDateOfBirth(customer.getDateOfBirth());
		entity.setEmailId(customer.getEmailId());
		entity.setName(customer.getName());
		entity.setCustomerType(customer.getCustomerType());
		entityManager.persist(entity);

		customerId = entity.getCustomerId();

		return customerId;

	}
	/**
	  This method will get details of customer from the database based on customerId received as parameter 
	  
	 
	  @param  -Integer customerId  
	  @return - Customer object populated using customerEntity fetched from entityManager 
	  
	 */
	public Customer getCustomer(Integer customerId) {

		Customer customer = null;

		CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, customerId);
		if (customerEntity != null) {
			customer = new Customer();
			customer.setCustomerId(customerEntity.getCustomerId());
			customer.setDateOfBirth(customerEntity.getDateOfBirth());
			customer.setEmailId(customerEntity.getEmailId());
			customer.setName(customerEntity.getName());
			customer.setCustomerType(customerEntity.getCustomerType());
		}

		return customer;
	}
	/**
	  This method will update emailId  of the customer present in  the database based on customerId received in parameter 
	  with the new emailId received in parameter.
	 
	  @param  -Integer customerId  String emailId
	  @return - Integer customerId of updated customer 
	  */
	public Integer updateCustomer(Integer customerId, String emailId) {

		Integer cId = null;

		CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, customerId);
		customerEntity.setEmailId(emailId);
		cId = customerEntity.getCustomerId();

		return cId;
	}
	/**
	  This method will delete details of customer from the database based on customerId received in parameter. 
	  
	  @param  -Integer customerId  
	  @return - Integer customerId of  customer removed from the database
	  */
	public Integer deleteCustomer(Integer customerId) {

		Integer cId = null;
		CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, customerId);
		entityManager.remove(customerEntity);
		cId = customerEntity.getCustomerId();

		return cId;
	}

}