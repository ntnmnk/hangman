package com.hitansh.hangman.services;

import java.util.List;
import java.util.UUID;

import com.hitansh.hangman.exceptions.InvalidInputException;
import com.hitansh.hangman.model.Word;
import com.hitansh.hangman.repositories.IWordRepository;
import com.hitansh.hangman.requests.AddWordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordService implements IWordService {

    private final IWordRepository wordRepository;

    public WordService(IWordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @Override
    public List<Word> getAllWords() {
        return wordRepository.getAllWords();
    }

    @Override
    public Word addWord(AddWordRequest addWordRequest) throws InvalidInputException {
          if(addWordRequest.getWord().isEmpty() || addWordRequest.getHint().isEmpty()){
            throw new InvalidInputException("Invalid input. Please provide all the required fields.");
        }
        else if(addWordRequest.getWord().length()<5){
            throw new InvalidInputException("Word should be atleast 5 characters long.");
        }
        else if(addWordRequest.getHint().length()<5){
            throw new InvalidInputException("Hint should be atleast 5 characters long.");
        }
        else if(!addWordRequest.getWord().matches("^[a-zA-Z ]+$")){
            throw new InvalidInputException("Invalid word. Please provide a valid word containing only alphabets and spaces.");
        }
        else if(wordRepository.checkIfWordExists(addWordRequest.getWord())) {
            throw new InvalidInputException("Word already exists. Please provide a different word.");
        }
        String wordId = UUID.randomUUID().toString();
        Word word = new Word(wordId, addWordRequest.getWord().toLowerCase(), addWordRequest.getHint());
        wordRepository.addWord(word);
        return word;  
    }

    @Override
    public String deleteWord(String wordId) throws InvalidInputException{
        if(wordId.isEmpty()){
            throw new InvalidInputException("Invalid input. Please provide a valid word id.");
        }
        else if(!wordRepository.checkIfWordIdExists(wordId)){
            throw new InvalidInputException("Invalid word id. Please provide a valid word id.");
        }
        wordRepository.deleteWord(wordId);
        return wordId;
        
    }
    
}
