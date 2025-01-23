package com.ezbank.accounts;

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
		title = "Accounts Microservice REST API Documentation",
		description = "EzBank Accounts microservice REST API Documentation",
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
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}