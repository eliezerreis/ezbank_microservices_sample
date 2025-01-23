package com.ezbank.loans.entity;

import com.ezbank.loans.constant.LoanType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "loan")
@Getter
@Setter
@ToString
public class Loan extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    private String mobileNumber;

    private String loanNumber;

    @Enumerated(EnumType.STRING)
    private LoanType loanType;

    private int totalLoan;

    private int amountPaid;

    private int outstandingAmount;
}
