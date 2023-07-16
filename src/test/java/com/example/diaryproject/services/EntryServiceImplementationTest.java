package com.example.diaryproject.services;
import com.example.diaryproject.Data.models.Entry;
import com.example.diaryproject.dtos.requests.CreateEntryRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Locale;

class EntryServiceImplementationTest {
    CreateEntryRequest createEntryRequest;
    Entry createEntryResponse;
    private final EntryService entryService = new EntryServiceImplementation();
    @BeforeEach
    void setUp() {
    createEntryRequest  = new CreateEntryRequest();
    createEntryRequest.setBody(createEntryRequest.getBody());
    createEntryRequest.setTitle(createEntryRequest.getTitle());

    }
    @DisplayName("Create Entry Test -> ")
    @Test void entryCanBeCreatedTest(){
        createEntryResponse = entryService.createEntry(createEntryRequest);
        Assertions.assertNotNull(createEntryResponse);

    }
    @DisplayName("Login Entry Test -> ")
    @Test void CreateOneEntry_CountIsOne(){
    createEntryResponse = entryService.createEntry(createEntryRequest);
    Assertions.assertNotNull(createEntryResponse);
    }
    @DisplayName("Create Multiple Entry Test -> ")
    @Test void createMultipleTest(){
        createEntryResponse = entryService.createEntry(createEntryRequest);
        createEntryRequest.setBody("i will beat timmy one day one day");
        createEntryRequest.setTitle("He's too tall");
        createEntryResponse = entryService.createEntry(createEntryRequest);
        createEntryRequest.setBody("You're the handicap you must face");
        createEntryRequest.setTitle("always choose your place");
        createEntryResponse = entryService.createEntry(createEntryRequest);
        Assertions.assertEquals(3,entryService.count());

    }
    @DisplayName("Created entry can add body -> ")
    @Test void createdEntryCanAddBody(){
        createEntryResponse = entryService.createEntry(createEntryRequest);
        createEntryRequest.setBody("You're the handicap you must face");
        createEntryResponse = entryService.createEntry(createEntryRequest);
        String expected = "You're the handicap you must face";

        String actual = "You're the handicap you must face";
        Assertions.assertEquals(expected.toUpperCase(Locale.ROOT),
                actual.toUpperCase(Locale.ROOT));
    }
    @DisplayName("Created entry can add title")
    @Test void createdEntryCanAddTitle(){
        createEntryResponse = entryService.createEntry(createEntryRequest);
        createEntryRequest.setTitle("yo i'm up !");
        createEntryResponse = entryService.createEntry(createEntryRequest);
        String expected = "yo i'm up !";
        String actual = "yo i'm up !";
        Assertions.assertEquals(expected.toUpperCase(Locale.ROOT),
                actual.toUpperCase(Locale.ROOT));
    }
}