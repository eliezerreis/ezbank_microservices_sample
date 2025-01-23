package com.ezbank.cards.entity;

import com.ezbank.cards.constant.CardType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "card")
@Getter
@Setter
@ToString
public class Card extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    private String mobileNumber;

    private String cardNumber;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    private int totalLimit;

    private int amountUsed;

    private int availableAmount;
}
