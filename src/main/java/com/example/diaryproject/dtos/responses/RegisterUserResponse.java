package com.example.diaryproject.dtos.responses;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class RegisterUserResponse {
    @Id
    private String id;
    private String message;

}
