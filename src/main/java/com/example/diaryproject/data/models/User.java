package com.example.diaryproject.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@Document(collection = "users")
public class User {

        private String username;
        private LocalDateTime dateTime = LocalDateTime.now();
        private String emailAddress;
        private String password;
        @Id
        private String id;

}
