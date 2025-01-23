package com.ezbank.accounts.audit;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditAware")
@OpenAPIDefinition(
    info = @Info(
        title = "Accounts microservice REST API Documentation",
        description = "EzBank Accounts microservice REST API Documentation",
        version = "v1",
        contact = @Contact(
            name = "Eliezer Oliveira",
            email = "eliezerreis@gmail.com"
        ),
        license = @License(
            name = "Apache 2.0",
            url = "https://www.eazybytes.com"
        )
    )
)
public class AuditAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of( "Anonymous");
    }
}
