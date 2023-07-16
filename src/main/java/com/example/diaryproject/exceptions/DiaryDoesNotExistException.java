package com.example.diaryproject.exceptions;

public class DiaryDoesNotExistException extends Exception{
    public DiaryDoesNotExistException(String message){
        super(message);
    }
}
