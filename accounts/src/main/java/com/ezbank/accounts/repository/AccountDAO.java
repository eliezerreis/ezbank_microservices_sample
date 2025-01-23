package com.ezbank.accounts.repository;

import com.ezbank.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountDAO extends JpaRepository<Account, Long> {

    Optional<Account> findByCustomerId(Long customerId);
}
