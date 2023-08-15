package com.example.diaryproject.utils;

import com.example.diaryproject.exceptions.UserDoesNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public String handleException(UserDoesNotExistException e){
        return e.getMessage();
    }
}
