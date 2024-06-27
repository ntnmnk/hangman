package com.hitansh.hangman.services;

import java.util.List;

import com.hitansh.hangman.exceptions.InvalidInputException;
import com.hitansh.hangman.model.Word;
import com.hitansh.hangman.requests.AddWordRequest;
import org.springframework.stereotype.Service;

@Service
public interface IWordService {

    List<Word> getAllWords();

    Word addWord(AddWordRequest addWordRequest) throws InvalidInputException;

    String deleteWord(String wordId) throws InvalidInputException;    
}
