package com.example.diaryproject.Data.repository;


import com.example.diaryproject.Data.models.Entry;

import java.util.ArrayList;
import java.util.Objects;

public class EntryRepositoryImplementation implements EntryRepository {

    private int count = 0;
    ArrayList<Entry> entries = new ArrayList<>();
    @Override
    public void create(Entry entry) {
        entry.setId(generateEntryId());
        entries.add(entry);
        count+=1;

    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public Entry findById(int id) {
        for (Entry entry: entries) {
            if (entry.getId() == id) return entry;
        }
        return null;
    }

    @Override
    public Entry findByTitle(String title) {
        for (Entry entry: entries) {
            if (Objects.equals(entry.getTitle(), title)) return entry;
        }return null;
    }

    @Override
    public void deleteBy(int id) {
        for (Entry entry :entries) {if (entry.getId()==id)entries.remove(entry);
            count-=1;break;
            
        }
    }

    @Override
    public Entry deleteBy(String title) {
        for (Entry entry : entries) {
            if (Objects.equals(entry.getTitle(), title)) entries.remove(entry);
            count -= 1;
            break;
        }
        return null;
    }

    @Override
    public int update(Entry entry) {
        for (Entry entry1 : entries)
            if (Objects.equals(entry1.getId(), entry)) {
                entries.remove(entry1);
                count -= 1;
                entry.setId(generateEntryId());
                entries.add(entry1);
                count += 1;
            }
        return 0;
    }

    @Override
    public void deleteA() {
        entries.clear();
    }

    private int generateEntryId() {
        return count;
    }
}
