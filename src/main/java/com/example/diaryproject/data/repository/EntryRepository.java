package com.example.diaryproject.data.repository;

import com.example.diaryproject.data.models.Entry;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EntryRepository extends MongoRepository<Entry, String> {
    Optional<Entry> findById(String id);
}