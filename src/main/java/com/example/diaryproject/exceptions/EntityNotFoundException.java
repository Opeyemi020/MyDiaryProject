package com.example.diaryproject.exceptions;

public class EntityNotFoundException extends Error{
    public EntityNotFoundException(String message) {
        super(message);
    }
}
