package com.example.card_service.repository;

import com.example.card_service.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query(value = "select * from card as c where c.user_id=?1", nativeQuery = true)
    List<Card> selectByUserId(Long userId);
}
