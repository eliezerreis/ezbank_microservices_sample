package com.ezbank.cards.repository;

import com.ezbank.cards.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardDAO extends JpaRepository<Card, Long> {

    Optional<Card> findByMobileNumber(String mobileNumber);

    Optional<Card> findByCardNumber(String cardNumber);
}
