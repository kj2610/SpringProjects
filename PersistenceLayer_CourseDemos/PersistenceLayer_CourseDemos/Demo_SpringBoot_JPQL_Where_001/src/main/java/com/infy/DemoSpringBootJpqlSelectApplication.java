package com.infy;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.infy.dto.CustomerDTO;
import com.infy.service.CustomerService;

@SpringBootApplication
public class DemoSpringBootJpqlSelectApplication implements CommandLineRunner {

	private static final Log LOGGER = LogFactory.getLog(DemoSpringBootJpqlSelectApplication.class);

	@Autowired
	CustomerService service;

	@Autowired
	Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBootJpqlSelectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		getCustomerdetails();
	}

	public void getCustomerdetails() {

		try {
			List<CustomerDTO> customerDTOs = service.getCustomerdetails(1002);

			for (CustomerDTO customerDTO : customerDTOs) {
				LOGGER.info(customerDTO);
			}

		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),
					"Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}
	}

}
