package com.example.diaryproject.repository;

import com.example.diaryproject.Data.models.Entry;
import com.example.diaryproject.Data.repository.EntryRepository;
import com.example.diaryproject.Data.repository.EntryRepositoryImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntryRepositoryImplementationTest {
    EntryRepository entryRepository;

    @BeforeEach
    void setUp() {
        entryRepository = new EntryRepositoryImplementation();
    }
    @Test public void Entry_Exist_Test(){
        assertNotNull(entryRepository);
    }
    @Test public void Create_Entry_Test(){
        Entry aiyeolaEntry = new Entry();
        Entry oluwaseyiEntry = new Entry();
        Entry rettnaEntry = new Entry();
        Entry sultyEntry = new Entry();
        entryRepository.create(aiyeolaEntry);
        entryRepository.create(rettnaEntry);
        entryRepository.create(oluwaseyiEntry);
        entryRepository.create(sultyEntry);
        assertEquals(4,entryRepository.count());

    }
    @Test public void find_Entry_By_Id_test(){
        Entry aiyeolaEntry = new Entry();
        Entry oluwaseyiEntry = new Entry();
        Entry rettnaEntry = new Entry();
        Entry sultyEntry = new Entry();
        entryRepository.create(aiyeolaEntry);
        entryRepository.create(rettnaEntry);
        entryRepository.create(oluwaseyiEntry);
        entryRepository.create(sultyEntry);
        assertEquals(4,entryRepository.count());
        entryRepository.findById(2);
        assertEquals(2,oluwaseyiEntry.getId());


    }
    @Test public void find_Entry_By_Title_Test(){
        Entry aiyeolaEntry = new Entry();
        Entry oluwaseyiEntry = new Entry();
        Entry rettnaEntry = new Entry();
        Entry sultyEntry = new Entry();
        sultyEntry.setTitle("Life is hard");
        sultyEntry.setBody("Ile aye le");
        entryRepository.create(aiyeolaEntry);
        entryRepository.create(rettnaEntry);
        entryRepository.create(oluwaseyiEntry);
        entryRepository.create(sultyEntry);
        assertEquals(4,entryRepository.count());
        entryRepository.findByTitle("Life is hard");
        entryRepository.findByTitle("Life is hard");
        assertEquals("Ile aye le", sultyEntry.getBody());
    }
    @Test public void delete_Entry_By_Id_Test(){
        Entry aiyeolaEntry = new Entry();
        Entry oluwaseyiEntry = new Entry();
        Entry rettnaEntry = new Entry();
        Entry sultyEntry = new Entry();
        entryRepository.create(aiyeolaEntry);
        entryRepository.create(rettnaEntry);
        entryRepository.create(oluwaseyiEntry);
        entryRepository.create(sultyEntry);
        assertEquals(4,entryRepository.count());
        entryRepository.deleteByTitle(aiyeolaEntry.getId());
        assertEquals(3,entryRepository.count());
    }
    @Test public void delete_By_Title_Test(){
        Entry aiyeolaEntry = new Entry();
        Entry oluwaseyiEntry = new Entry();
        Entry rettnaEntry = new Entry();
        Entry sultyEntry = new Entry();
        sultyEntry.setTitle("Life is hard");
        sultyEntry.setBody("Ile aye le");
        entryRepository.create(aiyeolaEntry);
        entryRepository.create(rettnaEntry);
        entryRepository.create(oluwaseyiEntry);
        entryRepository.create(sultyEntry);
        assertEquals(4,entryRepository.count());
        entryRepository.deleteByTitle("Life is hard");
        assertEquals(3,entryRepository.count());
    }
    @Test public void save_Two_update_One_Test(){
        Entry aiyeolaEntry = new Entry();
        Entry oluwaseyiEntry = new Entry();
        entryRepository.create(aiyeolaEntry);
        oluwaseyiEntry.setId(2);
        entryRepository.create(oluwaseyiEntry);
        assertEquals(2,entryRepository.count());
        entryRepository.update(aiyeolaEntry);
        assertEquals(2,entryRepository.count());
    }
}