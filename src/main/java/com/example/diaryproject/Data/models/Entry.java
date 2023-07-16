package com.example.diaryproject.Data.models;
import lombok.Data;

@Data
public class Entry  {
    private String title;
    private String body;
    private int id;

    @Override
    public String toString() {
        return String.format("%s%s",title, body);
    }
}
