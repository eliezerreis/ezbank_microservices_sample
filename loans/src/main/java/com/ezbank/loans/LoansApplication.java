package com.ezbank.loans;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing (auditorAwareRef = "auditAware")
@OpenAPIDefinition(
	info = @Info(
		title = "Loans Microservice REST API Documentation",
		description = "EzBank Loans Microservice REST API Documentation",
		version = "v1",
		contact = @Contact(
			name = "Eliezer Oliveira",
			email = "eliezerreis@gmail.com"
		),
		license = @License(
			name = "Apache 2.0"
		)
	)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}