package com.infy;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.infy.dto.CustomerDTO;

import io.netty.handler.codec.http.HttpMethod;
@SpringBootApplication
public class DemoRestTemplateApplication implements CommandLineRunner {
	private static final Logger LOGGER = LogManager.getLogger(DemoRestTemplateApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(DemoRestTemplateApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		getCustomerDetails(2);
	}
	public void getCustomerDetails(Integer customerId) {
	    String url = "http://localhost:8766/infybank/customers/{customerId}";
	    WebClient webClient = WebClient.create();
	    CustomerDTO customerDTO = webClient.get().uri(url,customerId).retrieve().bodyToMono(CustomerDTO.class).block();
	    LOGGER.info(customerDTO);
	   
	}

}