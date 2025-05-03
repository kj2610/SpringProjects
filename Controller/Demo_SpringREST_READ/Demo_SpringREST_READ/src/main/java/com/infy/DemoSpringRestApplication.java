package com.infy;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.infy.dto.CustomerDTO;

@SpringBootApplication
public class DemoSpringRestApplication{

	
	public static void  main(String[] args) {
		SpringApplication.run(DemoSpringRestApplication.class, args);
	}
	
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
