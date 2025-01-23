package com.ezbank.accounts.entity;

import com.ezbank.accounts.constants.AccountType;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Table (name = "account")
@Getter @Setter @ToString
public class Account extends BaseEntity{

    @Id
    private Long accountNumber;

    private Long customerId;

    @Enumerated(STRING)
    private AccountType accountType;

    private String branchAddress;
}
