package com.infy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dao.CustomerDAO;
import com.infy.model.Customer;

@Service(value = "customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;
	/**
	  This method  calls getCustomer method of CustomerDAOImpl by passing customerId received in parameter
	  to get corresponding customer details
	 
	  @param  -Integer customerId  
	  @return - Customer object returned from getCustomer method of CustomerDAOImpl
	  @throws - Service.CUSTOMER_UNAVAILABLE exception if Customer object returned from getCustomer method of CustomerDAOImpl is null
	 */
	public Customer getCustomer(Integer customerId) throws Exception {

		Customer customer = customerDAO.getCustomer(customerId);

		if (customer == null) {
			throw new Exception("Service.CUSTOMER_UNAVAILABLE");
		}

		return customer;
	}
	/**
	  This method  first calls getCustomer method of CustomerDAOImpl by passing customerId from customer object received in parameter
	  to check whether customer already exist in database or not.If customer object  received in parameter are not present in database then calls
	  addCustomer method of CustomerDAOImpl to add customer details to the database
	 
	  @param  -Customer object 
	 
	  @throws - Service.CUSTOMER_ALREADY_EXISTS exception if Customer object returned from getCustomer method of CustomerDAOImpl is not null
	 */
	public Integer addCustomer(Customer customer) throws Exception {

		if (customerDAO.getCustomer(customer.getCustomerId()) != null) {
			throw new Exception("Service.CUSTOMER_ALREADY_EXISTS");
		}

		return customerDAO.addCustomer(customer);

	}
	/**
	  This method  first calls getCustomer method of CustomerDAOImpl by passing customerId  received in parameter
	  to check whether customer  exist in database or not.If return from  getCustomer method of CustomerDAOImpl is not null then calls
	  updateCustomer method of CustomerDAOImpl to update existing customer emailId 
	  
	 
	  @param  -Interger customerId, String latest emailId 
	 
	  @throws - Service.CUSTOMER_UNAVAILABLE exception if return from  getCustomer of CustomerDAOImpl is null
	 */
	public Integer updateCustomer(Integer customerId, String emailId)
			throws Exception {
		Customer customer = customerDAO.getCustomer(customerId);

		if (customer == null) {
			throw new Exception("Service.CUSTOMER_UNAVAILABLE");
		}
		return customerDAO.updateCustomer(customerId, emailId);
	}
	/**
	  This method  first calls getCustomer method of CustomerDAOImpl by passing customerId  received in parameter
	  to check whether customer  exist in database or not.If return from  getCustomer method of CustomerDAOImpl is not null then calls
	  deleteCustomer method of CustomerDAOImpl to remove  customer details from the database 
	  
	 
	  @param  -Interger customerId
	 
	  @throws - Service.CUSTOMER_UNAVAILABLE exception if return from  getCustomer of CustomerDAOImpl is null
	 */
	public Integer deleteCustomer(Integer customerId) throws Exception {
		Customer customer = customerDAO.getCustomer(customerId);

		if (customer == null) {
			throw new Exception("Service.CUSTOMER_UNAVAILABLE");
		}
		return customerDAO.deleteCustomer(customerId);
	}

}
