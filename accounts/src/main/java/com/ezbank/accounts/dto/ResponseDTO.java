package com.ezbank.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data @AllArgsConstructor
public class ResponseDTO {

    private HttpStatus statusCode;
    private String message;
}
