package com.hitansh.hangman.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "words")
public class Word {

    @Id
    @Column(name = "word_id", nullable = false)
    private String wordId;

    @Column(name = "word", nullable = false)
    private String word;

    @Column(name = "hint", nullable = false)
    private String hint;


    public Word(String wordId, String word, String hint) {
        this.wordId = wordId;
        this.word = word;
        this.hint = hint;
    }

    public Word() {

    }

    public String getWordId() {
        return wordId;
    }

    public void setWordId(String wordId) {
        this.wordId = wordId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
    

    public String getObscuredWord(List<String> guessedAlphabets) {

        String wordToDisplay = "";
        for (int i = 0; i < this.word.length(); i++) {
            String currentLetter = this.word.substring(i, i + 1);
            if (guessedAlphabets.contains(currentLetter)) {
                wordToDisplay += currentLetter;
            } else {
                wordToDisplay += "_";
            }
        }

        return wordToDisplay;
    }
}
