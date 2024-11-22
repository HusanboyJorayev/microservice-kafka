package com.example.user_service.service.impl;

import com.example.user_service.dto.CardDto;
import com.example.user_service.dto.UserDto;
import com.example.user_service.dto.UserWithCards;
import com.example.user_service.entity.User;
import com.example.user_service.mapper.UserMapper;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<?> create(UserDto.CreatUser creatUser) {
        User entity = this.userMapper.toEntity(creatUser);
        entity.setCreatedAt(LocalDateTime.now());
        this.userRepository.save(entity);
        return ResponseEntity.ok("CREATED");
    }

    @Override
    public ResponseEntity<?> get(Long id) {
        Optional<User> optional = this.userRepository.findById(id);
        if (optional.isPresent()) {
            User user = optional.get();
            UserDto dto = this.userMapper.toDto(user);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.ok("USER IS NOT FOUND THIS ID ; " + id);
    }

    @Override
    public ResponseEntity<?> getAll() {
        List<User> userList = this.userRepository.findAll();
        if (!userList.isEmpty()) {
            return ResponseEntity.ok(this.userMapper.dtoList(userList));
        }
        return ResponseEntity.ok(new ArrayList<>());
    }

    @Override
    public ResponseEntity<?> getWithCards(Long userid) {
        UserWithCards userWithCards = new UserWithCards();

        ParameterizedTypeReference<List<CardDto>> typeRef = new ParameterizedTypeReference<List<CardDto>>() {
        };
        List<CardDto> cards = this.restTemplate.exchange(
                "http://localhost:2222/card/getAllByUserid?userId=" + userid,
                HttpMethod.GET,
                null,
                typeRef
        ).getBody();

        Optional<User> optional = this.userRepository.findById(userid);
        if (optional.isPresent()) {
            User user = optional.get();
            if (cards != null && !cards.isEmpty()) {
                UserDto dto = this.userMapper.toDto(user);
                userWithCards.setUserDto(dto);
                userWithCards.setCardDtoList(cards);
            }
        }
        return ResponseEntity.ok(userWithCards);
    }
}
