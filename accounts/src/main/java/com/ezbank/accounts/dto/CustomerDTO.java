package com.ezbank.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {

    @NotEmpty (message = "Name cannot be null or empty.")
    @Size (min = 5, max = 50)
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})")
    private String mobileNumber;

    private AccountDTO account;
}
