package com.example.diaryproject.Data.repository;

import com.example.diaryproject.Data.models.Diary;

import java.util.List;

public interface DiaryRepository {

    Diary save(Diary diary);

    long count();
    Diary findBy(int id);
    Diary findBy(String username);

    void update(Diary diary);

    void deleteBy(String username);
    void deleteBy(int Id);
    List<Diary> findAll();
}

