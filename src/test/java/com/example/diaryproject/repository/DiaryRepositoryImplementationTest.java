package com.example.diaryproject.repository;

import com.example.diaryproject.Data.models.Diary;
import com.example.diaryproject.Data.repository.DiaryRepository;
import com.example.diaryproject.Data.repository.DiaryRepositoryImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiaryRepositoryImplementationTest {
    DiaryRepository diaryRepository;

    @BeforeEach
    void startWith() {
        diaryRepository = new DiaryRepositoryImplementation();
    }
    @Test public void Object_exist_Test(){
        assertNotNull(diaryRepository);
    }
    @Test public void diary_Can_Be_Saved_Test(){
        diaryRepository.save(new Diary());
        assertEquals(1,diaryRepository.count());
    }

    @Test
    public void save_One_Diary_Count_Is_One_Test() {
        Diary diary = new Diary();
        diaryRepository.save(diary);
        assertEquals(1, diaryRepository.count());
    }

    @Test
    public void save_Two_Diary_Count_Is_Two_Test() {
        Diary diary = new Diary();
        Diary retnaDiary = new Diary();
        diaryRepository.save(diary);
        diaryRepository.save(retnaDiary);
        assertEquals(2, diaryRepository.count());
    }

    @Test
    public void save_Diary_find_Diary_By_Id_Test() {
        Diary diary = new Diary();
        Diary retnaDiary = new Diary();
        diaryRepository.save(diary);
        diaryRepository.save(retnaDiary);
        assertEquals(2, diaryRepository.count());
        diaryRepository.findBy(diary.getId());
        assertEquals(retnaDiary, diaryRepository.findBy(retnaDiary.getId()));
    }
    @Test public void save_Two_Diary_Update_One_test(){
        Diary Sultandiary = new Diary();
        Diary AiyeolaDiary = new Diary();
        diaryRepository.save(Sultandiary);
        AiyeolaDiary.setId(1);
        diaryRepository.save(AiyeolaDiary);
        assertEquals(2,diaryRepository.count());
        diaryRepository.update(AiyeolaDiary);
        assertEquals(2,diaryRepository.count());
    }
    @Test public void can_Find_Diary_By_Id_Test(){
        Diary Sultandiary = new Diary();
        Diary AiyeolaDiary = new Diary();
        diaryRepository.save(Sultandiary);
        diaryRepository.save(AiyeolaDiary);
        diaryRepository.findBy(Sultandiary.getId());
        assertEquals(Sultandiary,diaryRepository.findBy(Sultandiary.getId()));

    }
    @Test public void can_Find_Diary_By_username_Test() {
        Diary Sultandiary = new Diary();
        Diary AiyeolaDiary = new Diary();
        diaryRepository.save(Sultandiary);
        diaryRepository.save(AiyeolaDiary);
        assertEquals(2,diaryRepository.count());
        diaryRepository.findBy(Sultandiary.getUsername());
        assertEquals(Sultandiary, diaryRepository.findBy(Sultandiary.getUsername()));
    }
    @Test public void can_Delete_Diary_By_username_Test() {
        Diary Sultandiary = new Diary();
        Diary AiyeolaDiary = new Diary();
        diaryRepository.save(Sultandiary);
        diaryRepository.save(AiyeolaDiary);
        assertEquals(2, diaryRepository.count());
        String username = Sultandiary.getUsername();
        diaryRepository.deleteBy(username);
        assertEquals(1,diaryRepository.count());
    }
    @Test public void can_Delete_Diary_By_Id_Test() {
        Diary Sultandiary = new Diary();
        Diary AiyeolaDiary = new Diary();
        diaryRepository.save(Sultandiary);
        diaryRepository.save(AiyeolaDiary);
        assertEquals(2, diaryRepository.count());
        int diaryId = Sultandiary.getId();
        diaryRepository.deleteBy(diaryId);
        assertEquals(1, diaryRepository.count());
    }
}