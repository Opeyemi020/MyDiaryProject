package com.example.diaryproject.exceptions;

public class DiaryUsernameAlreadyExistExceptions extends IllegalArgumentException{
    public DiaryUsernameAlreadyExistExceptions(String message){
        super(message);
    }
}
