package com.example.diaryproject.dtos.responses;

import lombok.Data;

@Data
public class CreateEntryResponse {
    private String title;
    private String message;
}
