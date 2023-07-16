package com.example.diaryproject.services;

import com.example.diaryproject.Data.models.Entry;
import com.example.diaryproject.Data.repository.EntryRepository;
import com.example.diaryproject.Data.repository.EntryRepositoryImplementation;
import com.example.diaryproject.dtos.requests.CreateEntryRequest;

import static com.example.diaryproject.utils.Mapper.map;

public class EntryServiceImplementation implements EntryService{
    private final static EntryRepository entryRepository = new EntryRepositoryImplementation();


    @Override
    public Entry createEntry(CreateEntryRequest createEntryRequest) {
        Entry entry = map(createEntryRequest);
        entryRepository.create(entry);
        map(entry);
        return entry;
    }

    @Override
    public int count() {
        return entryRepository.count();
    }

    @Override
    public void deleteAll() {
        entryRepository.deleteA();
    }
}
