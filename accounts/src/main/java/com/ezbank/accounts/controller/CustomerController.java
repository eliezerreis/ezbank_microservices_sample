package com.ezbank.accounts.controller;

import com.ezbank.accounts.dto.CustomerDTO;
import com.ezbank.accounts.service.CustomerService;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping(value = "/api/customer", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController {

    private Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;


    @Autowired
    public CustomerController(CustomerService customerService, StreamBridge streamBridge) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO customer = customerService.create(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @GetMapping("/{mobileNumber}")
    @Retry(name = "fetchCustomer", fallbackMethod = "fetchCustomerFallback")
    public ResponseEntity<CustomerDTO> fetchCustomer(
        @RequestHeader("ezbank-correlation-id") String correlationId,
        @PathVariable @NotNull String mobileNumber) throws TimeoutException {

        logger.info("Fetch Customer Method Invoked");

        // Simulate sm error to run the exponential backoffice strategy
        throw new TimeoutException("Error while fetching customers");
    }

    // After the retries the fallback will recovery from the failure
    public ResponseEntity<CustomerDTO> fetchCustomerFallback(String correlationId, String mobileNumber, Throwable throwable) {
        logger.debug("ezbank-correlation-id: {}", correlationId);

        CustomerDTO customer = customerService.fetch(mobileNumber);

        // Simulate na error to run the exponential backoffice strategy
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @PutMapping
    public ResponseEntity<CustomerDTO> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO customer = customerService.update(customerDTO);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @DeleteMapping("/{mobileNumber}")
    public ResponseEntity<CustomerDTO> deleteCustomer(@PathVariable @NotNull String mobileNumber) {
        customerService.delete(mobileNumber);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}