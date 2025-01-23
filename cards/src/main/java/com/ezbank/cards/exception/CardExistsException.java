package com.ezbank.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CardExistsException extends RuntimeException {
    public CardExistsException(String message) {
        super(message);
    }

    public CardExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CardExistsException(Throwable cause) {
        super(cause);
    }

}
