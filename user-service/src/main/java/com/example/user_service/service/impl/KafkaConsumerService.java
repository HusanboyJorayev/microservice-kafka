package com.example.user_service.service.impl;

import com.example.user_service.dto.CardDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "java", groupId = "hero")
    public void consume(ConsumerRecord<String, String> record) {
        try {
            String userId = record.key();
            String cardDtosJson = record.value();
            List<CardDto> cardDtos = objectMapper.readValue(cardDtosJson, new TypeReference<>() {});

            System.out.println("Received cards for userId: " + userId + " -> " + cardDtos);
        } catch (Exception e) {
            System.err.println("Failed to process message: " + e.getMessage());
        }
    }
}

