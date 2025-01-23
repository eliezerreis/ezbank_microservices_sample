package com.ezbank.accounts.entity;

import com.ezbank.accounts.dto.AccountDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "customer")
@Setter @Getter @ToString
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String name;

    private String email;

    private String mobileNumber;

}
