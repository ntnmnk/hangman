package com.hitansh.hangman.controller;

import java.util.List;

import com.hitansh.hangman.exceptions.InvalidInputException;
import com.hitansh.hangman.exceptions.NoWordsAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hitansh.hangman.requests.GuessRequest;
import com.hitansh.hangman.responses.GameByGameIdResponse;
import com.hitansh.hangman.responses.GetAllGamesOfUserResponse;
import com.hitansh.hangman.responses.GetGameStatisticsResponse;
import com.hitansh.hangman.responses.GuessResponse;
import com.hitansh.hangman.responses.NewGameResponse;
import com.hitansh.hangman.responses.QuitGameResponse;
import com.hitansh.hangman.responses.SaveGuessByUserResponse;
import com.hitansh.hangman.services.IGameService;
import com.hitansh.hangman.exceptions.BadRequestException;
import com.hitansh.hangman.model.Game;
import com.hitansh.hangman.model.GameStatistics;
import com.hitansh.hangman.model.User;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class GameController {

    @Autowired
    private IGameService gameService;

    @GetMapping("/game")
    public ResponseEntity<NewGameResponse> getNewGame(HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = ((User) auth.getPrincipal()).getUserId();
        try {
            Game newGame = gameService.getNewGame(userId);
            return ResponseEntity.ok(new NewGameResponse(newGame));
        }
        catch (NoWordsAvailableException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<GameByGameIdResponse> getGameByGameId(@PathVariable String gameId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = ((User) auth.getPrincipal()).getUserId();
        try {
            Game game = gameService.getGameByGameId(userId, gameId);
            return ResponseEntity.ok(new GameByGameIdResponse(
                    game.getWord().getObscuredWord(game.getGuessedAlphabets()),
                    game.getWord().getHint(), game.getRemainingLives(), game.getGuessedAlphabets(), game.getScore()));
        } catch (InvalidInputException e) {
            throw new BadRequestException(e.getMessage());
        }

    }

    @GetMapping("/games")
    public ResponseEntity<GetAllGamesOfUserResponse> getAllGamesOfUser(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = ((User) auth.getPrincipal()).getUserId();
            List<Game> userGames = gameService.getAllGamesOfUser(userId);
            return ResponseEntity.ok(new GetAllGamesOfUserResponse(userGames));
    }

    @PostMapping("/game/{gameId}/guess")
    public ResponseEntity<SaveGuessByUserResponse> saveGuessByUser(@PathVariable String gameId,
            @RequestBody GuessRequest guessRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = ((User) auth.getPrincipal()).getUserId();
        try {
            GuessResponse guessResponse = gameService.saveGuessByUser(userId, gameId, guessRequest);
            Game game = guessResponse.getGame();
            boolean isCorrectGuess = guessResponse.isCorrectGuess();
            return ResponseEntity
                    .ok(new SaveGuessByUserResponse(game.getWord().getObscuredWord(game.getGuessedAlphabets()),
                            game.getRemainingLives(), game.getGuessedAlphabets(), isCorrectGuess, game.getGameStatus(),
                            game.getScore()));
        } catch (InvalidInputException e) {
            throw new BadRequestException(e.getMessage());
        }

    }

    @PostMapping("/game/{gameId}/quit")
    public ResponseEntity<QuitGameResponse> quitGame(@PathVariable String gameId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = ((User) auth.getPrincipal()).getUserId();
        try {
            Game game = gameService.quitGame(userId,gameId);
            return ResponseEntity.ok(new QuitGameResponse(game));
        } catch (InvalidInputException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @GetMapping("/game-statistics")
    public ResponseEntity<GetGameStatisticsResponse> getGameStatistics() {
            List<GameStatistics> gameStatistics = gameService.getGameStatistics();
            return ResponseEntity.ok(new GetGameStatisticsResponse(gameStatistics));
    }
}
