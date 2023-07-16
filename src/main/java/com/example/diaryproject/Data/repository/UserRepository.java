package com.example.diaryproject.Data.repository;

import com.example.diaryproject.Data.models.User;

public interface UserRepository {
    void save(User user);
    int count();

    User findBy(int id) ;

    void deleteById(int id);

    User findByUsername(String username);

    void deleteBy(String username);


}
