package com.example.diaryproject.data.repository;

import com.example.diaryproject.data.models.Diary;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiaryRepository extends MongoRepository<Diary, String> {
    Diary findByUsername(String username);
}
