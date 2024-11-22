package com.example.card_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDto {
    private Long id;
    private String number;
    private String name;
    private String code;
    private String type;
    private String validDate;
    private Long userId;
    private LocalDateTime createdAt;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreateCard {
        private String number;
        private String name;
        private String code;
        private String type;
        private Long userId;
        private String validDate;
    }
}
