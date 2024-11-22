package com.example.user_service.controller;

import com.example.user_service.dto.UserDto;
import com.example.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody UserDto.CreatUser creatUser) {
        return this.userService.create(creatUser);
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam("id") Long id) {
        return this.userService.get(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return this.userService.getAll();
    }

    @GetMapping("/getWithCards")
    public ResponseEntity<?> getWithCards(@RequestParam("userId") Long userid) {
        return this.userService.getWithCards(userid);
    }
}
