package com.hitansh.hangman.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hitansh.hangman.requests.AddWordRequest;
import com.hitansh.hangman.responses.AddWordResponse;
import com.hitansh.hangman.responses.DeleteWordResponse;
import com.hitansh.hangman.responses.GetAllWordsResponse;
import com.hitansh.hangman.services.IWordService;
import com.hitansh.hangman.exceptions.BadRequestException;
import com.hitansh.hangman.exceptions.InvalidInputException;
import com.hitansh.hangman.model.Word;

@RestController
public class WordController {

    @Autowired
    private IWordService wordService;

    @GetMapping("/words")
    public ResponseEntity<GetAllWordsResponse> getAllWords() {
            List<Word> words = wordService.getAllWords();
            return ResponseEntity.ok(new GetAllWordsResponse(words));

    }

    @PostMapping("/word")
    public ResponseEntity<AddWordResponse> addWord(@RequestBody AddWordRequest addWordRequest) {
        try {
            Word word = wordService.addWord(addWordRequest);
            return ResponseEntity.ok(new AddWordResponse(word));
        } catch (InvalidInputException e) {
            throw new BadRequestException(e.getMessage());
        }


    }

    @PostMapping("/word/{wordId}")
    public ResponseEntity<DeleteWordResponse> deleteWord(@PathVariable String wordId) {
        try {
            String deletedWordId = wordService.deleteWord(wordId);
            return ResponseEntity.ok(new DeleteWordResponse(deletedWordId));
        } catch (InvalidInputException e) {
            throw new BadRequestException(e.getMessage());
        }

    }
}
