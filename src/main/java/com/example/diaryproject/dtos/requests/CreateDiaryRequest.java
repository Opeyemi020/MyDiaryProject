package com.example.diaryproject.dtos.requests;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data

public class CreateDiaryRequest {
    private String username;
    private String emailAddress;
    private String password;
    private int id;
}
