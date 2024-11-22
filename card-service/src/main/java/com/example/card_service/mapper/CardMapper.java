package com.example.card_service.mapper;

import com.example.card_service.dto.CardDto;
import com.example.card_service.entity.Card;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class CardMapper {
    public CardDto toDto(Card card) {
        return CardDto.builder()
                .id(card.getId())
                .number(card.getNumber())
                .name(card.getName())
                .code(card.getCode())
                .type(card.getType())
                .userId(card.getUserId())
                .validDate(card.getValidDate())
                .createdAt(card.getCreatedAt())
                .build();
    }

    public Card toEntity(CardDto.CreateCard createCard) {
        return Card.builder()
                .code(createCard.getCode())
                .number(createCard.getNumber())
                .name(createCard.getName())
                .type(createCard.getType())
                .userId(createCard.getUserId())
                .validDate(createCard.getValidDate())
                .build();
    }

    public List<CardDto> dtoList(List<Card> cards) {
        if (cards != null && !cards.isEmpty()) {
            return cards.stream().map(this::toDto).toList();
        }
        return new ArrayList<>();
    }
}
