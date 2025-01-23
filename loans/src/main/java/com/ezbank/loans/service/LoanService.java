package com.ezbank.loans.service;

import com.ezbank.loans.dto.LoanDTO;

public interface LoanService {

    LoanDTO create(String mobileNumber);

    LoanDTO fetch(String mobileNumber);

    LoanDTO update(LoanDTO loanDTO);

    void delete(String mobileNumber);
}
