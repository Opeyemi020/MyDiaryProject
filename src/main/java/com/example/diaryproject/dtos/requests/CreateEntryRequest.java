package com.example.diaryproject.dtos.requests;

import lombok.Data;

@Data
public class CreateEntryRequest {
    private int id;
    private String title;
    private String body;
}
