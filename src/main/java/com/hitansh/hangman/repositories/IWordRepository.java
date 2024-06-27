package com.hitansh.hangman.repositories;

import java.util.List;

import com.hitansh.hangman.exceptions.NoWordsAvailableException;
import org.springframework.stereotype.Repository;

import com.hitansh.hangman.model.DatabaseRequestStatus;
import com.hitansh.hangman.model.Word;

@Repository
public interface IWordRepository {

    Word getRandomWord() throws NoWordsAvailableException;

    List<Word> getAllWords();

    // This enum makes no sense. It should be named something meaningful. Create another enum for database status codes. They will be very different from HTTP status codes. JPA status codes can be used to indicate if a record was inserted, updated, or deleted.
    DatabaseRequestStatus addWord(Word newWord);

    DatabaseRequestStatus deleteWord(String wordId);

    boolean checkIfWordExists(String word);

    boolean checkIfWordIdExists(String wordId); 
}
