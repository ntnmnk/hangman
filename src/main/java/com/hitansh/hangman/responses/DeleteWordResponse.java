package com.hitansh.hangman.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteWordResponse {

    private String deletedWordId;

    public DeleteWordResponse(String deletedWordId) {
        this.deletedWordId = deletedWordId;
    }

    public String getDeletedWordId() {
        return deletedWordId;
    }

    public void setDeletedWordId(String deletedWordId) {
        this.deletedWordId = deletedWordId;
    }
    
    
}
