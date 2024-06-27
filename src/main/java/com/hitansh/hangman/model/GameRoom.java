package com.hitansh.hangman.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class GameRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomId;
    @OneToMany
    private List<User> players;
    @OneToOne(cascade = CascadeType.ALL)
    private Game gameState;

    public GameRoom() {
    }


    public GameRoom(Long id, String roomId, List<User> players, Game gameState) {
        this.id = id;
        this.roomId = roomId;
        this.players = players;
        this.gameState = gameState;
    }
}