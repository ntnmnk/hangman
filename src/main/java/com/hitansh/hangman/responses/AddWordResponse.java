package com.hitansh.hangman.responses;

import com.hitansh.hangman.model.Word;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddWordResponse {

    private Word word;

    public AddWordResponse(Word word) {
        this.word = word;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
    
}
