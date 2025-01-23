package com.ezbank.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerExistsException extends RuntimeException {
    public CustomerExistsException(String message) {
        super(message);
    }

    public CustomerExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerExistsException(Throwable cause) {
        super(cause);
    }

}
