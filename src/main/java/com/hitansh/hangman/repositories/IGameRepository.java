package com.hitansh.hangman.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hitansh.hangman.model.DatabaseRequestStatus;
import com.hitansh.hangman.model.Game;

@Repository
public interface IGameRepository {
    
    Game createGame(Game newGame);

    DatabaseRequestStatus saveGame(String gameId, Game game);
    
    Game getGameByGameId(String gameId);

    List<Game> getAllGamesOfUser(String userId);

    Game saveGuessByUser(String guess, String gameId);

    DatabaseRequestStatus quitGame(String userId, String gameId);

    boolean checkIfGuessAlreadyMade(String gameId, String lowerCase);

}
