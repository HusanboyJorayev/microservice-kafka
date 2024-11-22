package com.example.user_service.service;

import com.example.user_service.dto.UserDto;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    ResponseEntity<?> create(UserDto.CreatUser creatUser);

    ResponseEntity<?> get(Long id);

    ResponseEntity<?> getAll();

    ResponseEntity<?> getWithCards(Long userid);
}
