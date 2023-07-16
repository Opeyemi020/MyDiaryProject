package com.example.diaryproject.dtos.responses;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class LoginDiaryResponse {
    private int id;
    private String message;
    private final LocalDateTime localDateTime = LocalDateTime.now();

}
