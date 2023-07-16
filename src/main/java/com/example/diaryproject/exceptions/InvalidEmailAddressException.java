package com.example.diaryproject.exceptions;

public class InvalidEmailAddressException extends Exception{
    public InvalidEmailAddressException(String message){
        super(message);
    }

}
