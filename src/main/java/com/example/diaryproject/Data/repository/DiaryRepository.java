package com.example.diaryproject.Data.repository;

import com.example.diaryproject.Data.models.Diary;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DiaryRepository extends MongoRepository<Diary, String> {
    Diary findByUsername(String username);
}
