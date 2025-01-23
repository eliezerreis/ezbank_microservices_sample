package com.ezbank.cards.service.impl;

import com.ezbank.cards.constant.CardType;
import com.ezbank.cards.dto.CardDTO;
import com.ezbank.cards.entity.Card;
import com.ezbank.cards.exception.CardExistsException;
import com.ezbank.cards.exception.ResourceNotFoundException;
import com.ezbank.cards.mapper.CardMapper;
import com.ezbank.cards.repository.CardDAO;
import com.ezbank.cards.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardMapper cardMapper;
    private CardDAO cardDAO;

    @Override
    public CardDTO create(String mobileNumber) {
        Optional<Card> optionalCards= cardDAO.findByMobileNumber(mobileNumber);
        if(optionalCards.isPresent()){
            throw new CardExistsException("Card already registered with given mobileNumber "+mobileNumber);
        }
        Card card = createDefaultCard(mobileNumber);
        card = cardDAO.save(card);

        return cardMapper.toDTO(card);
    }

    private Card createDefaultCard(String mobileNumber) {
        Card newCard = new Card();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardType.CREDIT_CARD);
        newCard.setTotalLimit(1000);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(1000);
        return newCard;
    }


    @Override
    public CardDTO fetch(String mobileNumber) {
        Card card = cardDAO.findByMobileNumber(mobileNumber).orElseThrow(
            () -> new ResourceNotFoundException("Card doesn't exist on the database.")
        );
        return cardMapper.toDTO(card);
    }

    @Override
    public CardDTO update(CardDTO cardDTO) {
        Card card = cardDAO.findByCardNumber(cardDTO.getCardNumber()).orElseThrow(
            () -> new ResourceNotFoundException("Card doesn't exist on the database."));
        cardMapper.updateFromDTO(cardDTO, card);
        cardDAO.save(card);

        return cardMapper.toDTO(card);
    }

    @Override
    public void delete(String mobileNumber) {
        Card cards = cardDAO.findByMobileNumber(mobileNumber).orElseThrow(
            () -> new ResourceNotFoundException("Card doesn't exist on the database.")
        );
        cardDAO.deleteById(cards.getCardId());
    }

}