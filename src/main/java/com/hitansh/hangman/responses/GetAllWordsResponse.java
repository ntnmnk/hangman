package com.hitansh.hangman.responses;

import java.util.List;

import com.hitansh.hangman.model.Word;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAllWordsResponse {

    private List<Word> words;

    public GetAllWordsResponse(List<Word> words) {
        this.words = words;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }
    
}
