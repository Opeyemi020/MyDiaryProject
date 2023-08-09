package com.example.diaryproject.dtos.responses;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data

public class CreateDiaryResponse {
    private String message;
    @Id
    private String id;
}
