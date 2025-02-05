package com.ezbank.loans.controller;

import com.ezbank.loans.dto.LoanDTO;
import com.ezbank.loans.service.LoanService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/loan", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class LoanController {

    private final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<LoanDTO> createLoan(@Valid @RequestBody String mobileNumber) {
        LoanDTO customer = loanService.create(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @GetMapping("/{mobileNumber}")
    public ResponseEntity<LoanDTO> fetchLoan(@PathVariable @NotNull String mobileNumber) {
        LoanDTO customer = loanService.fetch(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @PutMapping
    public ResponseEntity<LoanDTO> updateLoan(@Valid @RequestBody LoanDTO customerDTO) {
        LoanDTO customer = loanService.update(customerDTO);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @DeleteMapping("/{mobileNumber}")
    public ResponseEntity<LoanDTO> deleteLoan(@PathVariable @NotNull String mobileNumber) {
        loanService.delete(mobileNumber);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}