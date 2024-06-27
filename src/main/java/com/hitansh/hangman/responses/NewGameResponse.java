package com.hitansh.hangman.responses;

import com.hitansh.hangman.model.Game;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewGameResponse{

    private String gameId;

    public NewGameResponse(Game game) {
        this.gameId = game.getGameId();
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
