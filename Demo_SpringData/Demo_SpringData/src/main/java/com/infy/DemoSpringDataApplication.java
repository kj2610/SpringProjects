package com.infy;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.infy.dto.CustomerDTO;
import com.infy.entity.Customer;
import com.infy.exception.InfyBankException;
import com.infy.repository.CustomerRepository;
import com.infy.service.CustomerServiceImpl;

@SpringBootApplication
public class DemoSpringDataApplication implements CommandLineRunner{
	private static final org.apache.logging.log4j.Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(DemoSpringDataApplication.class);
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CustomerServiceImpl customerService;
	
//	@Autowired
//	Environment environment;
	
	@Autowired
	org.springframework.core.env.Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringDataApplication.class, args);
	}
	
	public void run(String... args) throws Exception{

		
//		System.out.println("In Main Class");
//		Customer ct1 = new Customer(2, "kajal@gmail.com", "kajal", LocalDate.of(1987, 4, 2));
//		Customer ct2 = new Customer(3, "kajal@gmail.com", "kajal", LocalDate.of(1987, 4, 2));
//		
//		customerRepository.save(ct1);
//		customerRepository.save(ct2);
//		
//		LOGGER.info("Customer Fetch by id");
//		Customer ct3 = customerRepository.findById(1).get();
//		LOGGER.info(ct3);
////		System.out.println(ct3);
//		
//		
//		LOGGER.info("All Customers");
//		
//		Iterable<Customer> customers = customerRepository.findAll();
//		customers.forEach(LOGGER::info);
		addCustomer();
		updateCustomer(2, "kj@gmail.com");
		getCustomer(2);
		getAllCustomer();
		
		
	}
	public void addCustomer() {
		CustomerDTO customer = new CustomerDTO();
		customer.setCustomerDTO(4);
		customer.setEmaildId("chiru@gmail.com");
		customer.setName("Chiru");
		customer.setDateofBirth(LocalDate.now());
		
		try {
			customerService.addCustomer(customer);
			LOGGER.info(environment.getProperty("UserInterface.INSERT_SUCCESS"));
			
		} catch (Exception e) {
			// TODO: handle exception
			if(e.getMessage() != null) {
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
			}
		}
				
	}
	public void updateCustomer(Integer id, String emailId) throws InfyBankException {
		customerService.updateCustomer(id, emailId);
		LOGGER.info(environment.getProperty("UserInterface.UPDATE_SUCCESS"));
	}
	
	public void getCustomer(Integer id) throws InfyBankException {
		CustomerDTO customerDTO = customerService.getCustomer(id);
		LOGGER.info("Get Customer Details");
		LOGGER.info(customerDTO);
	}
	public void getAllCustomer() throws InfyBankException {
		List<CustomerDTO> customer = customerService.getAllCustomer();
		customer.forEach(LOGGER::info);
	}

}
