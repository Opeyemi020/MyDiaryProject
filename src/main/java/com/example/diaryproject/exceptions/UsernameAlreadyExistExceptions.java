package com.example.diaryproject.exceptions;

import java.security.PublicKey;

public class UsernameAlreadyExistExceptions extends IllegalArgumentException{
    public UsernameAlreadyExistExceptions (String message){
        super(message);
    }
}
