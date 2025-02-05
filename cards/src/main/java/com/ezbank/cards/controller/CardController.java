package com.ezbank.cards.controller;

import com.ezbank.cards.dto.CardDTO;
import com.ezbank.cards.service.CardService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/card", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<CardDTO> createCard(@Valid @RequestBody String mobileNumber) {
        CardDTO customer = cardService.create(mobileNumber);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @GetMapping("/{mobileNumber}")
    public ResponseEntity<CardDTO> fetchCard(@PathVariable @NotNull String mobileNumber) {
        CardDTO customer = cardService.fetch(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @PutMapping
    public ResponseEntity<CardDTO> updateCard(@Valid @RequestBody CardDTO customerDTO) {
        CardDTO customer = cardService.update(customerDTO);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @DeleteMapping("/{mobileNumber}")
    public ResponseEntity<CardDTO> deleteCard(@PathVariable @NotNull String mobileNumber) {
        cardService.delete(mobileNumber);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/fallback")
    public ResponseEntity<String> fallback() {
        return ResponseEntity.status(HttpStatus.OK).body("Service temporarily unavailable. Please try again later.");
    }
}