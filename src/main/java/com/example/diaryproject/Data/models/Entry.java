package com.example.diaryproject.Data.models;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "entries")
public class Entry  {
    private String title;
    private String body;
    @Id
    private String id;
    @Override
    public String toString() {
        return String.format("%s%s",title, body);
    }
}
