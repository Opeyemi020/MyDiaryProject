package com.example.diaryproject.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DiaryExceptionHandler {

    @ExceptionHandler(DiaryException.class)
    public ResponseEntity<ExceptionResponse>
    exceptionHandler(DiaryException exception){
        ExceptionResponse response = ExceptionResponse
                .builder()
                .message(exception.getMessage())
                .status(exception.getStatus())
                .build();
        return new ResponseEntity<>(response, exception.getStatus());
    }

}
