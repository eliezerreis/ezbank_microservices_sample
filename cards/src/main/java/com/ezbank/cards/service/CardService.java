package com.ezbank.cards.service;

import com.ezbank.cards.dto.CardDTO;

public interface CardService {

    CardDTO create(String mobileNumber);

    CardDTO fetch(String mobileNumber);

    CardDTO update(CardDTO cardDTO);

    void delete(String mobileNumber);
}
