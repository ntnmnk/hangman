package com.hitansh.hangman.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.hitansh.hangman.model.Game;
import com.hitansh.hangman.model.GameStatus;
import com.hitansh.hangman.model.DatabaseRequestStatus;
import com.hitansh.hangman.model.Word;

import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
@Primary
public class MySQLGameRepository implements IGameRepository{

    Connection connection;
    PreparedStatement statement;
    DataSource dataSource;  

    public MySQLGameRepository(DataSource dataSource) throws SQLException {

        this.dataSource = dataSource;
        connection = dataSource.getConnection();
    }

    @Override
    public Game createGame(Game newGame) {
        try {
            statement = connection.prepareStatement("INSERT INTO game (game_id, wordId, remaining_lives, user_id, game_status, score) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, newGame.getGameId());
            statement.setString(2, newGame.getWord().getWordId());
            statement.setInt(3, newGame.getRemainingLives());
            statement.setString(4, newGame.getUserId());
            statement.setString(5, newGame.getGameStatus().toString());
            statement.setInt(6, newGame.getScore());
            statement.executeUpdate();
            log.info("Game created");
            return newGame;
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error in creating game");
            throw new RuntimeException("Error in creating game");
        }
      
    }

    @Override
    public DatabaseRequestStatus saveGame(String gameId, Game game) {
        try{
            statement = connection.prepareStatement("UPDATE game SET remaining_lives = ?, game_status = ?, score = ? WHERE game_id = ?");
            statement.setInt(1, game.getRemainingLives());
            statement.setString(2, game.getGameStatus().toString());
            statement.setInt(3, game.getScore());
            statement.setString(4, gameId);
            statement.executeUpdate();
            log.info("Game saved");
            return DatabaseRequestStatus.SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error in saving game");
            throw new RuntimeException("Error in saving game");
        }
        
    }

    @Override
    public Game getGameByGameId(String gameId) {
        try{
            statement = connection.prepareStatement("SELECT W.word, W.hint, W.wordId, G.remaining_lives, G.user_id, G.game_status, G.score, GROUP_CONCAT(GU.guess SEPARATOR ',') AS Guesses from game G LEFT JOIN word W ON G.wordId = W.wordId LEFT JOIN Guess GU ON G.game_id = GU.game_id WHERE G.game_id = ? GROUP BY G.game_id");
            statement.setString(1, gameId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                    String word = resultSet.getString("word");
                    String hint = resultSet.getString("hint");
                    String wordId = resultSet.getString("wordId");
                    Word wordObject = new Word(wordId, word, hint);
                    String guesses = resultSet.getString("Guesses");
                    List<String> guessedAlphabetsList = new ArrayList<>();
                    if(guesses != null){
                        String[] guessedAlphabets = guesses.split(",");
                        for(String guess: guessedAlphabets){
                            guessedAlphabetsList.add(guess);
                        }
                    }
                    log.info("Game fetched by gameId");
            return new Game(gameId, wordObject, resultSet.getInt("remaining_lives"),
            guessedAlphabetsList, resultSet.getString("user_id"), GameStatus.valueOf(resultSet.getString("game_status")),
            resultSet.getInt("score"));
            }
            log.info("Game not found by gameId");
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error in getting game by gameId");
            throw new RuntimeException("Error in getting game by gameId");
        }
    }

    @Override
    public List<Game> getAllGamesOfUser(String userId) {
        try{

            statement = connection.prepareStatement("SELECT W.wordId, W.word, W.hint, G.remaining_lives, G.game_id, G.game_status, G.score, GROUP_CONCAT(GU.guess SEPARATOR ',') AS Guesses from game G LEFT JOIN word W ON G.wordId = W.wordId LEFT JOIN Guess GU ON G.game_id = GU.game_id WHERE G.user_id = ? GROUP BY GU.game_id");
            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();
            List<Game> games = new ArrayList<>();
            while(resultSet.next()){
                String word = resultSet.getString("word");
                String hint = resultSet.getString("hint");
                String wordId = resultSet.getString("wordId");
                Word wordObject = new Word(wordId, word, hint);
                String guesses = resultSet.getString("Guesses");
                List<String> guessedAlphabetsList = new ArrayList<>();
                if(guesses != null){
                    String[] guessedAlphabets = guesses.split(",");
                    for(String guess: guessedAlphabets){
                        guessedAlphabetsList.add(guess);
                    }
                }
                Game game = new Game(resultSet.getString("gameId"), wordObject, resultSet.getInt("remaining_lives"),
                guessedAlphabetsList, userId, GameStatus.valueOf(resultSet.getString("game_status")),
                resultSet.getInt("score"));
                games.add(game);
            }
            log.info("All games of user fetched");
            return games;
        } catch (SQLException e) {
            log.error("Error in getting all games of user");
            e.printStackTrace();
            throw new RuntimeException("Error in getting all games of user");
        }
    }

    @Override
    public Game saveGuessByUser(String guess, String gameId) {
        try{
            statement = connection.prepareStatement("SELECT * FROM game WHERE game_id = ?");
            statement.setString(1, gameId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                statement = connection.prepareStatement("Insert into guess (game_id, guess) VALUES (?, ?)");
                statement.setString(1, gameId);
                statement.setString(2, guess);
                statement.executeUpdate();
                log.info("Guess saved");
                return getGameByGameId(gameId);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error in saving guess by user");
            throw new RuntimeException("Error in saving guess by user");
        }
    }

    @Override
    public DatabaseRequestStatus quitGame(String userId, String gameId) {
        try{
            statement = connection.prepareStatement("UPDATE game SET game_status = ? WHERE game_id = ? AND user_id = ?");
            statement.setString(1, GameStatus.QUIT.toString());
            statement.setString(2, gameId);
            statement.setString(3, userId);
            statement.executeUpdate();
            log.info("Game quit");
            return DatabaseRequestStatus.SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error in quitting game");
            throw new RuntimeException("Error in quitting game");
        }
        
    }

    @Override
    public boolean checkIfGuessAlreadyMade(String gameId, String lowerCase) {
        try{
            statement = connection.prepareStatement("SELECT * FROM guess WHERE game_id = ? AND guess = ?");
            statement.setString(1, gameId);
            statement.setString(2, lowerCase);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                log.info("Guess already made");
                return true;
            }
            log.info("Guess not made yet");
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Error in checking if guess already made");
            throw new RuntimeException("Error in checking if guess already made");
    }
    
}
}
