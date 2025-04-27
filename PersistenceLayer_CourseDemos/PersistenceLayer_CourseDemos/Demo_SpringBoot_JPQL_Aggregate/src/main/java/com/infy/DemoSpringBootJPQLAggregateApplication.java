package com.infy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.infy.service.CustomerService;

@SpringBootApplication
public class DemoSpringBootJPQLAggregateApplication implements CommandLineRunner {

	private static final Log LOGGER = LogFactory.getLog(DemoSpringBootJPQLAggregateApplication.class);

	@Autowired
	CustomerService service;

	@Autowired
	Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBootJPQLAggregateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		getAverageBalance();
//		getTotalBalance();
//		getNumberOfAccounts();
//		getMinimumBalance();
//		getMaximumBalance();
	}

	public void getMaximumBalance() {
		Integer maxBalance;
		try {
			maxBalance = service.getMaximumBalance();
			LOGGER.info(environment.getProperty("UserInterface.MAX_BALANCE") + maxBalance);
			LOGGER.info("\n");
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),
					"Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}

	}

	public void getMinimumBalance() {
		Integer minimumBalance;
		try {
			minimumBalance = service.getMinimumBalance();
			LOGGER.info(environment.getProperty("UserInterface.MIN_BALANCE") + minimumBalance);
			LOGGER.info("\n");
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),
					"Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}

	}

	public void getNumberOfAccounts() {
		Long numberOfAccounts;
		try {
			numberOfAccounts = service.getNumberOfAccounts();
			LOGGER.info(environment.getProperty("UserInterface.NO_OF_ACCOUNTS") + numberOfAccounts);
			LOGGER.info("\n");
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),
					"Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}

	}

	public void getTotalBalance() {
		Long totalBalance;
		try {
			totalBalance = service.getTotalBalance();
			LOGGER.info(environment.getProperty("UserInterface.TOTAL_BALANCE") + totalBalance);
			LOGGER.info("\n");
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),
					"Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}

	}

	public void getAverageBalance() {
		Double averageBalance;
		try {
			averageBalance = service.getAverageBalance();
			LOGGER.info(environment.getProperty("UserInterface.AVERAGE_BALANCE") + averageBalance);
			LOGGER.info("\n");
		} catch (Exception e) {
			String message = environment.getProperty(e.getMessage(),
					"Some exception occured. Please check log file for more details!!");
			LOGGER.info(message);
		}
	}

}
