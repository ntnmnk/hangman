package com.hitansh.hangman.responses;

import com.hitansh.hangman.model.Game;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuitGameResponse {

    private Game game;

    public QuitGameResponse(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
     
}
