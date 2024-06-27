package com.hitansh.hangman.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "game_statistics")
public class GameStatistics {

    @Id
    private String id;

    @Column(name = "total_games_played", nullable = false)
    private int totalGamesPlayed;

    @Column(name = "total_games_won", nullable = false)
    private int totalGamesWon;

    @Column(name = "total_games_lost", nullable = false)
    private int totalGamesLost;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Column(name = "total_score", nullable = false)
    private int totalScore;
    public GameStatistics(int totalGamesPlayed, int totalGamesWon, int totalGamesLost, String displayName, int totalScore) {
        this.totalGamesPlayed = totalGamesPlayed;
        this.totalGamesWon = totalGamesWon;
        this.totalGamesLost = totalGamesLost;
        this.displayName = displayName;
        this.totalScore = totalScore;
    }

    public GameStatistics() {

    }

    public int getTotalGamesPlayed() {
        return totalGamesPlayed;
    }

    public void setTotalGamesPlayed(int totalGamesPlayed) {
        this.totalGamesPlayed = totalGamesPlayed;
    }

    public int getTotalGamesWon() {
        return totalGamesWon;
    }

    public void setTotalGamesWon(int totalGamesWon) {
        this.totalGamesWon = totalGamesWon;
    }

    public int getTotalGamesLost() {
        return totalGamesLost;
    }

    public void setTotalGamesLost(int totalGamesLost) {
        this.totalGamesLost = totalGamesLost;
    }

    public String getDisplayName() {
        return displayName;
    }       

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
    
}
