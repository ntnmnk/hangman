package com.hitansh.hangman.responses;

import java.util.ArrayList;
import java.util.List;

import com.hitansh.hangman.model.Game;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAllGamesOfUserResponse {
    
    private List<Game> gamesWithoutWord;
    
    public GetAllGamesOfUserResponse(List<Game> games) {
        this.gamesWithoutWord = new ArrayList<Game>();
        for(Game game: games) {
            game.setWord(null);
            this.gamesWithoutWord.add(game);        
        }
    }

    public List<Game> getGamesWithoutWord() {
        return gamesWithoutWord;
    }

    public void setGamesWithoutWord(List<Game> gamesWithoutWord) {
        this.gamesWithoutWord = gamesWithoutWord;
    }

}
