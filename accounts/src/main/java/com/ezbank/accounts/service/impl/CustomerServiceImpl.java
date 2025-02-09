package com.ezbank.accounts.service.impl;

import com.ezbank.accounts.constants.AccountType;
import com.ezbank.accounts.dto.CustomerDTO;
import com.ezbank.accounts.entity.Account;
import com.ezbank.accounts.entity.Customer;
import com.ezbank.accounts.exception.CustomerExistsException;
import com.ezbank.accounts.exception.ResourceNotFoundException;
import com.ezbank.accounts.mapper.AccountMapper;
import com.ezbank.accounts.mapper.CustomerMapper;
import com.ezbank.accounts.repository.AccountDAO;
import com.ezbank.accounts.repository.CustomerDAO;
import com.ezbank.accounts.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final AccountMapper accountMapper;
    private final CustomerDAO customerDAO;
    private final AccountDAO accountDAO;

    @Autowired
    public CustomerServiceImpl(
        CustomerDAO customerDAO,
        AccountDAO accountDAO,
        AccountMapper accountMapper,
        CustomerMapper customerMapper) {

        this.accountMapper = accountMapper;
        this.customerMapper = customerMapper;
        this.customerDAO = customerDAO;
        this.accountDAO = accountDAO;
    }

    public CustomerDTO create(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);

        // Validates if  another customer has the same phone number;
        if (customerDAO.findCustomer(customer.getMobileNumber()).isPresent()) {
            throw new CustomerExistsException("The mobile number already exists.");
        }

        Customer newCustomer = customerDAO.save(customer);

        // Every time that new customer is created a new account should be
        // assigned to the new customer.
        Account account = createNewAccount(newCustomer);
        accountDAO.save(account);

        CustomerDTO result = customerMapper.toDTO(newCustomer);
        result.setAccount(accountMapper.toDTO(account));

        return result;
    }


    @Override
    public CustomerDTO fetch(String mobileNumber) {
        Optional<Customer> customer = customerDAO.findCustomer(mobileNumber);

        if (customer.isEmpty()) {
            throw new ResourceNotFoundException("Customer doesn't exist on the database.");
        }

        Optional<Account> acc = accountDAO.findByCustomerId(customer.get().getCustomerId());

        if (acc.isEmpty()) {
            throw new ResourceNotFoundException("There's no account for this customer.");
        }

        CustomerDTO customerDTO = customerMapper.toDTO(customer.get());
        customerDTO.setAccount(accountMapper.toDTO(acc.get()));

        return customerDTO;
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {

        Customer customer = customerDAO.findCustomer(customerDTO.getMobileNumber())
            .orElseThrow(() -> new ResourceNotFoundException("Customer doesn't exist on the database."));

        customerMapper.updateFromDTO(customerDTO, customer);
        Customer updatedCustomer = customerDAO.save(customer);

        Account account = accountDAO.findByCustomerId(customer.getCustomerId())
            .orElseThrow(() -> new ResourceNotFoundException("Account doesn't exist on the database."));

        accountMapper.updateFromDTO(customerDTO.getAccount(), account);
        Account updatedAccount = accountDAO.save(account);

        CustomerDTO result = customerMapper.toDTO(updatedCustomer);
        result.setAccount(accountMapper.toDTO(updatedAccount));
        return result;
    }

    @Override
    public void delete(String mobileNumber) {
        Customer customer = customerDAO.findCustomer(mobileNumber)
            .orElseThrow(() -> new ResourceNotFoundException("Customer doesn't exist on the database."));
        customerDAO.delete(customer);

        Account account = accountDAO.findByCustomerId(customer.getCustomerId())
            .orElseThrow(() -> new ResourceNotFoundException("Account doesn't exist on the database."));

        accountDAO.delete(account);
    }

    private Account createNewAccount(Customer customer) {
        Account account = new Account();
        account.setCustomerId(customer.getCustomerId());
        account.setAccountNumber(1L + new Random().nextInt(90000000));
        account.setAccountType(AccountType.SAVINGS);
        account.setBranchAddress("123 Main St., New York, NY");

        return account;
    }


}
