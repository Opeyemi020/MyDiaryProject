package com.example.diaryproject.exceptions;

public class WrongPasswordException extends Exception{
    public WrongPasswordException(String message){
        super(message);
    }
}
