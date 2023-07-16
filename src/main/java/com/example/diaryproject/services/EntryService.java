package com.example.diaryproject.services;


import com.example.diaryproject.Data.models.Entry;
import com.example.diaryproject.dtos.requests.CreateEntryRequest;

public interface EntryService {

    Entry createEntry(CreateEntryRequest createEntryRequest);

    int count();

    void deleteAll();
}
