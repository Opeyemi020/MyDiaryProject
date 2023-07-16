package com.example.diaryproject.dtos.requests;

import lombok.Data;

@Data
public class LoginDiaryRequest {
    private String username;
    private String password;
}
