package com.example.diaryproject.dtos.responses;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
@Data
public class LoginDiaryResponse {
    @Id
    private String id;
    private String message;
    private final LocalDateTime localDateTime = LocalDateTime.now();

}
