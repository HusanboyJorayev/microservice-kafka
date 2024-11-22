package com.example.card_service.controller;

import com.example.card_service.dto.CardDto;
import com.example.card_service.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/card")
public class CardController {
    private final CardService cardService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CardDto.CreateCard createCard) {
        return this.cardService.create(createCard);
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam("id") Long id) {
        return this.cardService.get(id);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return this.cardService.getAll();
    }

    @GetMapping("/getAllByUserid")
    public ResponseEntity<?> getAllByUserid(@RequestParam("userId") Long userid) {
        return this.cardService.getAllByUserid(userid);
    }
}
