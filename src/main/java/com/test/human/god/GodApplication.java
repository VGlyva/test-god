package com.test.human.god;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class GodApplication {

	public static void main(String[] args) {
		SpringApplication.run(GodApplication.class, args);
	}

}
