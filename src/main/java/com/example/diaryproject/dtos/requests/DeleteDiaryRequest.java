package com.example.diaryproject.dtos.requests;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "diaries")
public class DeleteDiaryRequest {
    private String username;
    @Id
    private String diaryId;
}
