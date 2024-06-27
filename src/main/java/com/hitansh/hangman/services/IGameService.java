package com.hitansh.hangman.services;

import java.util.List;

import com.hitansh.hangman.exceptions.InvalidInputException;
import com.hitansh.hangman.exceptions.NoWordsAvailableException;
import com.hitansh.hangman.model.Game;
import com.hitansh.hangman.model.GameStatistics;
import com.hitansh.hangman.requests.GuessRequest;
import com.hitansh.hangman.responses.GuessResponse;
import org.springframework.stereotype.Service;

@Service
public interface IGameService {

    Game getNewGame(String userId) throws NoWordsAvailableException;
    
    Game getGameByGameId(String userId, String gameId) throws InvalidInputException;

    List<Game> getAllGamesOfUser(String userId);

    GuessResponse saveGuessByUser(String userId, String gameId, GuessRequest guessRequest) throws InvalidInputException;

    Game quitGame(String userId, String gameId) throws InvalidInputException;

    List<GameStatistics> getGameStatistics();
    
}
