package com.hitansh.hangman.repositories;

import com.hitansh.hangman.model.GameRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRoomRepository extends JpaRepository<GameRoom, Long> {
    GameRoom findByRoomId(String roomId);
}
