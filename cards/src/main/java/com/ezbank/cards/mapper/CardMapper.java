package com.ezbank.cards.mapper;

import com.ezbank.cards.dto.CardDTO;
import com.ezbank.cards.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CardMapper {

    void updateFromDTO(CardDTO cardDTO, @MappingTarget Card card);

    CardDTO toDTO(Card card);

    Card toEntity(CardDTO cardDTO);
}
