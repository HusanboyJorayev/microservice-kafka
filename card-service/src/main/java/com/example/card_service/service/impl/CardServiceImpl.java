package com.example.card_service.service.impl;

import com.example.card_service.dto.CardDto;
import com.example.card_service.entity.Card;
import com.example.card_service.mapper.CardMapper;
import com.example.card_service.repository.CardRepository;
import com.example.card_service.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;


    @Override
    public ResponseEntity<?> create(CardDto.CreateCard createCard) {
        Card entity = this.cardMapper.toEntity(createCard);
        entity.setCreatedAt(LocalDateTime.now());
        this.cardRepository.save(entity);

        return ResponseEntity.ok("CARD CREATED");
    }

    @Override
    public ResponseEntity<?> get(Long id) {
        Optional<Card> optional = this.cardRepository.findById(id);
        if (optional.isPresent()) {
            Card card = optional.get();
            CardDto dto = this.cardMapper.toDto(card);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.ok("CARD IS NOT FOUND THIS ID : " + id);
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<Card> cards = this.cardRepository.findAll();
        if (!cards.isEmpty()) {
            return ResponseEntity.ok(this.cardMapper.dtoList(cards));
        }
        return ResponseEntity.ok(cards);
    }

    @Override
    public ResponseEntity<?> getAllByUserid(Long userid) {
        List<Card> cardList = this.cardRepository.selectByUserId(userid);
        if (cardList != null && !cardList.isEmpty()) {
            return ResponseEntity.ok(this.cardMapper.dtoList(cardList));
        }
        return ResponseEntity.ok(cardList);
    }
}
