package com.example.user_service.mapper;

import com.example.user_service.dto.UserDto;
import com.example.user_service.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .age(user.getAge())
                .createdAt(user.getCreatedAt())
                .build();
    }

    public User toEntity(UserDto.CreatUser creatUser) {
        return User.builder()
                .firstname(creatUser.getFirstname())
                .lastname(creatUser.getLastname())
                .email(creatUser.getEmail())
                .age(creatUser.getAge())
                .build();
    }

    public List<UserDto> dtoList(List<User> users) {
        if (users != null && !users.isEmpty()) {
            return users.stream().map(this::toDto).toList();
        }
        return new ArrayList<>();
    }
}
