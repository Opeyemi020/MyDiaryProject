package com.example.diaryproject.Data.models;

import jakarta.annotation.Generated;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data

@Document(collection = "diaries")
public class Diary{
    @Id
    private String id;
    private String username;
    private String password;
    private String emailAddress;
    private List<Entry> entries = new ArrayList<>();
    @CreatedDate
    private LocalDateTime dateTime;

}
