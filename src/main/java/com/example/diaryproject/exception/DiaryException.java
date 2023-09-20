package com.example.diaryproject.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class DiaryException extends RuntimeException{

    @Getter
    private HttpStatus status;

    public DiaryException(){
        super();
        this.status = HttpStatus.BAD_REQUEST;
    }
    public DiaryException(String message){
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }

    public DiaryException(HttpStatus status, String message){
        super(message);
        this.status = status;
    }
}
