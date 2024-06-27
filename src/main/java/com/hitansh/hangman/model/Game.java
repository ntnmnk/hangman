package com.hitansh.hangman.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @Column(name = "gameId", nullable = false)
    private String gameId;

    // Assuming Word is a separate entity. If not, adjust accordingly
    @ManyToOne
    private Word word;

    @Column(name = "remainingLives", nullable = false)
    private int remainingLives;

    @ElementCollection
    private List<String> guessedAlphabets;

    @Column(name = "userId", nullable = false)
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "gameStatus", nullable = false)
    private GameStatus gameStatus;

    @Column(name = "score", nullable = false)
    private int score;
    

    public Game(String gameId, Word word, int remainingLives, List<String> guessedAlphabets, String userId, GameStatus gameStatus, int score) {
        this.gameId = gameId;
        this.word = word;
        this.remainingLives = remainingLives;
        this.guessedAlphabets = guessedAlphabets;
        this.userId = userId;
        this.gameStatus = gameStatus;
        this.score = score;
    }

    public Game() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public String getGameId() {
        return gameId;
    }
    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
    public int getRemainingLives() {
        return remainingLives;
    }
    public void setRemainingLives(int remainingLives) {
        this.remainingLives = remainingLives;
    }
    public List<String> getGuessedAlphabets() {
        return guessedAlphabets;
    }
    public void setGuessedAlphabets(List<String> guessedAlphabets) {
        this.guessedAlphabets = guessedAlphabets;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public void addGuess(String guess) {
        guessedAlphabets.add(guess);
    }


    // @Override
    // public boolean equals(Object obj) {
    //     if (this == obj)
    //         return true;
    //     if (obj == null)
    //         return false;
    //     if (getClass() != obj.getClass())
    //         return false;
    //     Game other = (Game) obj;
    //     if (gameId == null) {
    //         if (other.gameId != null)
    //             return false;
    //     } else if (!gameId.equals(other.gameId))
    //         return false;
    //     return true;
    // }

    
}
