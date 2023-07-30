package com.example.diaryproject.Data.repository;
import com.example.diaryproject.Data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
