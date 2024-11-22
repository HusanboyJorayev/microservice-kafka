package com.example.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Integer age;
    private LocalDateTime createdAt;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CreatUser {
        private String firstname;
        private String lastname;
        private String email;
        private Integer age;

    }
}
