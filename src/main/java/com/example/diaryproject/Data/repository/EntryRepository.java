package com.example.diaryproject.Data.repository;

import com.example.diaryproject.Data.models.Entry;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EntryRepository extends MongoRepository<Entry, String> {
    Optional<Entry> findById(String id);
}