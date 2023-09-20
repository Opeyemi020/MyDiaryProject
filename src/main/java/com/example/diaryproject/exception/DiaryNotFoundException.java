package com.example.diaryproject.exception;

import org.springframework.http.HttpStatus;

public class DiaryNotFoundException extends DiaryException{

    public DiaryNotFoundException(){
        this("Diary Not Found");
    }

    public DiaryNotFoundException(String message){
        super(HttpStatus.NOT_FOUND, message);
    }
}
