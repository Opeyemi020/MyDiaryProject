package com.example.diaryproject.Data.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

        private String username;
        private LocalDateTime dateTime = LocalDateTime.now();
        private String emailAddress;
        private String password;
        private int id;

}
