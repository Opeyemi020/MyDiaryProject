package com.example.diaryproject.Data.repository;


import com.example.diaryproject.Data.models.Diary;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DiaryRepositoryImplementation implements DiaryRepository {
    List<Diary> diaries = new ArrayList<>();
    private int count = 0;

    @Override
    public Diary save(Diary diary) {
        diary.setId(generateDiaryId());
        diaries.add(diary);
        count += 1;
        return diary;
    }

    private int generateDiaryId() {
        return count;
    }

    @Override
    public long count() {
        return diaries.size();
    }

    @Override
    public Diary findBy(int id) {
        for (Diary diary : diaries) if (diary.getId() == id) return diary;
        return null;
    }

    @Override
    public Diary findBy(String username) {
        for (Diary diary : diaries) if (Objects.equals(diary.getUsername(), username)) return diary;
        return null;
    }

    @Override
    public void update(Diary diary) {
        for (Diary diary1 : diaries) if (Objects.equals(diary1.getId(), diary)) {
                diaries.remove(diary1);count -= 1;diary.setId(generateDiaryId());diaries.add(diary1);
                count += 1;
            }
        }

    @Override
    public void deleteBy(String username) {
        for (Diary diary : diaries) if (Objects.equals(diary.getUsername(), username)) {
                diaries.remove(diary);count -=1;
                break;
        }
    }

    @Override
    public void deleteBy(int Id) {
        for (Diary diary : diaries)if (diary.getId() == Id) {diaries.remove(diary);
                count -= 1;break;
        }
    }

    @Override
    public List<Diary> findAll() {
        return diaries;
    }
}
