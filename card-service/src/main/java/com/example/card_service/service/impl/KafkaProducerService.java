package com.example.card_service.service.impl;

import com.example.card_service.dto.CardDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    /**
     * Kafka brokerga xabar jo'natish uchun metod
     *
     * @param "topic  - java"   - Mavzu nomi
     * @param cardDto - Yuboriladigan xabar
     */
    public void sendUserCards(Long userId, List<CardDto> cardDto) {
        try {
            // CardDto'larni JSON formatiga o'zgartirish
            String cardDtosJson = objectMapper.writeValueAsString(cardDto);

            // Kafka'ga userId (key) va cardDtosJson (value) ni jo'natish
            System.out.println("Sending cards for userId: " + userId + " -> " + cardDtosJson);
            kafkaTemplate.send("java", String.valueOf(userId), cardDtosJson);
        } catch (JsonProcessingException e) {
            System.err.println("Failed to serialize cardDtos: " + e.getMessage());
        }
    }
}

