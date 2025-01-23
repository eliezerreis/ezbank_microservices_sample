package com.ezbank.accounts.repository;

import com.ezbank.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Long> {

    @Query ("SELECT c from Customer c where c.mobileNumber = :mobileNumber")
    Optional<Customer> findCustomer(String mobileNumber);
}
