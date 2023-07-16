package com.example.diaryproject.dtos.requests;

import lombok.Data;

@Data
public class CreateDiaryRequest {
    private String username;
    private String emailAddress;
    private String password;
    private int id;
}
