package com.example.diaryproject.services;

import com.example.diaryproject.Data.models.Entry;
import com.example.diaryproject.Data.repository.EntryRepository;
import com.example.diaryproject.dtos.requests.CreateEntryRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.diaryproject.utils.Mapper.map;
@Service
@AllArgsConstructor
public class EntryServiceImplementation implements EntryService{
    @Autowired
    private EntryRepository entryRepository;

    @Override
    public Entry createEntry(CreateEntryRequest createEntryRequest) {
        Entry entry = map(createEntryRequest);
        map(entry);
        entryRepository.save(entry);
        return entry;
    }

    @Override
    public int count() {
        return Math.toIntExact(entryRepository.count());
    }

    @Override
    public void deleteAll() {
        entryRepository.deleteAll();
    }
}
