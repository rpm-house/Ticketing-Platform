package com.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
	    info = @Info(
	        title = "My API",
	        version = "1.0",
	        description = "API documentation"
	    )
	)
@SpringBootApplication
public class TicketApp {

	
	public static void main(String[] args) {
		SpringApplication.run(TicketApp.class, args);
	}

}
