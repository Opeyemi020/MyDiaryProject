package com.example.diaryproject.services;
import com.example.diaryproject.dtos.requests.CreateDiaryRequest;
import com.example.diaryproject.dtos.requests.CreateEntryRequest;
import com.example.diaryproject.dtos.requests.LoginDiaryRequest;
import com.example.diaryproject.dtos.responses.CreateDiaryResponse;
import com.example.diaryproject.dtos.responses.CreateEntryResponse;
import com.example.diaryproject.dtos.responses.LoginDiaryResponse;
import com.example.diaryproject.exceptions.DiaryDoesNotExistException;
import com.example.diaryproject.exceptions.WrongPasswordException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiaryServiceImplementationTest {
    private CreateDiaryRequest createDiaryRequest;
    CreateEntryRequest createEntryRequest;

    private final DiaryService diaryService = new DiaryServiceImplementation();
    private CreateDiaryResponse createDiaryResponse;

    @BeforeEach
    void setUp() {
        createDiaryRequest = new CreateDiaryRequest();
    }
    @DisplayName("Created Diary is Not Empty Test -> ")
    @Test void createDiaryTest(){
        createDiaryRequest = new CreateDiaryRequest();
        createDiaryRequest.setUsername("aiyeola");
        createDiaryRequest.setEmailAddress("aiyeola@gmail.com");
        createDiaryRequest.setPassword("password");
         createDiaryResponse = diaryService.createDiary(createDiaryRequest);
        assertNotNull(createDiaryResponse);
    }
    @DisplayName("When dairy is created repository increases-> ")
    @Test void repositoryIncreasesWhenDairyIsCreatedTest(){
        createDiaryRequest = new CreateDiaryRequest();
        createDiaryRequest.setUsername("aiyeola");
        createDiaryRequest.setEmailAddress("aiyeola@gmail.com");
        createDiaryRequest.setPassword("password");
        createDiaryResponse = diaryService.createDiary(createDiaryRequest);
        assertNotNull(createDiaryResponse);
        assertEquals(1, diaryService.findAllDairy().size());
    }
    @DisplayName("LoggedIn Diary is Not Empty Test ->")
    @Test void loginDiaryTest() throws DiaryDoesNotExistException, WrongPasswordException {
        createDiaryRequest = new CreateDiaryRequest();
        createDiaryRequest.setUsername("aiyeola");
        createDiaryRequest.setEmailAddress("aiyeola@gmail.com");
        createDiaryRequest.setPassword("password");
        createDiaryResponse = diaryService.createDiary(createDiaryRequest);
        assertEquals(1, diaryService.findAllDairy().size());
        LoginDiaryRequest loginDiaryRequest = new LoginDiaryRequest();
        loginDiaryRequest.setUsername(createDiaryRequest.getUsername());
        loginDiaryRequest.setPassword(createDiaryRequest.getPassword());
        LoginDiaryResponse loginDiaryResponse = diaryService.loginDiary(loginDiaryRequest);
        assertNotNull(loginDiaryResponse);
    }
    @DisplayName("Diary Can Be Created Test ->")
    @Test void canCreateDiaryTest(){
           createDiaryRequest = new CreateDiaryRequest();
           createDiaryRequest.setUsername("aiyeola");
           createDiaryRequest.setEmailAddress("aiyeola@gmail.com");
           createDiaryRequest.setPassword("password");
           createDiaryResponse = diaryService.createDiary(createDiaryRequest);
           assertNotNull(createDiaryResponse);
           assertEquals(1, diaryService.count());
    }
    @DisplayName("Created diary is Allowed To Add Entry -> ")
    @Test void createdDiaryCanAddEntryTest() {
        createDiaryRequest = new CreateDiaryRequest();
        createDiaryRequest.setUsername("aiyeola");
        createDiaryRequest.setEmailAddress("aiyeola@gmail.com");
        createDiaryRequest.setPassword("password");
        createDiaryResponse = diaryService.createDiary(createDiaryRequest);
        assertEquals(1, diaryService.count());8
    }
}