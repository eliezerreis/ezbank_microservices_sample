package com.ezbank.accounts.service;

import com.ezbank.accounts.dto.CustomerDTO;

public interface CustomerService {
    CustomerDTO create(CustomerDTO customerDTO);

    CustomerDTO fetch(String mobileNumber);

    CustomerDTO update(CustomerDTO customerDTO);

    void delete(String mobileNumber);
}
