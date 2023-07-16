package com.example.diaryproject.Data.models;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Diary {
    private int id;
    private String username;
    private String password;
    private String emailAddress;
    private List<Entry> entries = new ArrayList<>();
    private LocalDateTime dateTime = LocalDateTime.now();

}
