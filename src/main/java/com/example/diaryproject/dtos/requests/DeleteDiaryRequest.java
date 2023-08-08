package com.example.diaryproject.dtos.requests;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class DeleteDiaryRequest {
    private String username;
    @Id
    private String id;
}
