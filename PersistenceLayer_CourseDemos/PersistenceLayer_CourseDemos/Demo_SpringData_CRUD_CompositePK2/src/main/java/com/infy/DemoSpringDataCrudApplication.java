package com.infy;

import java.time.LocalDate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.infy.dto.CustomerDTO;
import com.infy.entity.CustomerCompositeId;
import com.infy.service.CustomerServiceImpl;

@SpringBootApplication
public class DemoSpringDataCrudApplication implements CommandLineRunner {
	public static final Log LOGGER = LogFactory.getLog(DemoSpringDataCrudApplication.class);
	
	@Autowired
	CustomerServiceImpl customerService;

	@Autowired
	Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringDataCrudApplication.class, args);

	}

	public void run(String... args) throws Exception {
		addCustomer();
		//getCustomer();
		//findAllCustomers();
		//updateCustomer();
		//deleteCustomer();
	}

	public void addCustomer() {

		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setCustomerId(4);
		customerDTO.setEmailId("harry@infy.com");
		customerDTO.setName("Harry");
		customerDTO.setDateOfBirth(LocalDate.now());

		try {
			Integer id = customerService.addCustomer(customerDTO);
			LOGGER.info(environment.getProperty("UserInterface.INSERT_SUCCESS")+id);
		} catch (Exception e) {
			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}

	}

	public void getCustomer() {
		try {
			CustomerCompositeId customerCompositeId = new CustomerCompositeId(1,  "martin@infy.com");
			CustomerDTO customerDTO = customerService.getCustomer(customerCompositeId);
			LOGGER.info(customerDTO);
		} catch (Exception e) {

			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}
	}

	public void findAllCustomers() {
		try {
			customerService.findAll().forEach(LOGGER::info);
		} catch (Exception e) {

			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}
	}

	public void updateCustomer() {
		try {
			CustomerCompositeId customerCompositeId = new CustomerCompositeId(1,  "martin@infy.com");
			customerService.updateCustomer(customerCompositeId, "Martinez");
			LOGGER.info(environment.getProperty("UserInterface.UPDATE_SUCCESS"));
		} catch (Exception e) {

			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}
	}
	public void deleteCustomer() {
		try {
			CustomerCompositeId customerCompositeId = new CustomerCompositeId(1,  "martin@infy.com");
			customerService.deleteCustomer(customerCompositeId);
			LOGGER.info(environment.getProperty("UserInterface.DELETE_SUCCESS"));
		} catch (Exception e) {

			if (e.getMessage() != null)
				LOGGER.info(environment.getProperty(e.getMessage(),
						"Something went wrong. Please check log file for more details."));
		}
	}

}
