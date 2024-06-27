package com.hitansh.hangman.responses;

import java.util.List;

import com.hitansh.hangman.model.GameStatistics;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetGameStatisticsResponse {

    private List<GameStatistics> gameStatistics;

    public GetGameStatisticsResponse(List<GameStatistics> gameStatistics) {
        this.gameStatistics = gameStatistics;
    }

    public List<GameStatistics> getGameStatistics() {
        return gameStatistics;
    }

    public void setGameStatistics(List<GameStatistics> gameStatistics) {
        this.gameStatistics = gameStatistics;
    }

}
