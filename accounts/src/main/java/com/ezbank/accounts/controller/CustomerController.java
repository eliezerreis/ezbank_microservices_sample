package com.ezbank.accounts.controller;

import com.ezbank.accounts.dto.CustomerDTO;
import com.ezbank.accounts.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/customer", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO customer = customerService.create(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @GetMapping("/{mobileNumber}")
    public ResponseEntity<CustomerDTO> fetchCustomer(@PathVariable @NotNull String mobileNumber) {
        CustomerDTO customer = customerService.fetch(mobileNumber);
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