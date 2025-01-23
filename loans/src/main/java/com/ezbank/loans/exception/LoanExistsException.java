package com.ezbank.loans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LoanExistsException extends RuntimeException {
    public LoanExistsException(String message) {
        super(message);
    }

    public LoanExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoanExistsException(Throwable cause) {
        super(cause);
    }

}
