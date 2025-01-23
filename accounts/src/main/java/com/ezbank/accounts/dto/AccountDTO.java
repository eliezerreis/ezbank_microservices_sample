package com.ezbank.accounts.dto;

import com.ezbank.accounts.constants.AccountType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountDTO {

    @NotEmpty
    private Long accountNumber;

    @NotNull
    private AccountType accountType;

    @NotNull
    private String branchAddress;

}
