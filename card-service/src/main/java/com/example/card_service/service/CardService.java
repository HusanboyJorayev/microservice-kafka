package com.example.card_service.service;

import com.example.card_service.dto.CardDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface CardService {
    ResponseEntity<?> create(CardDto.CreateCard createCard);

    ResponseEntity<?> get(Long id);

    ResponseEntity<?> getAll();

    ResponseEntity<?> getAllByUserid(Long userid);
}
