package com.example.diaryproject.services;

import com.example.diaryproject.dtos.requests.CreateDiaryRequest;
import com.example.diaryproject.dtos.requests.DeleteDiaryRequest;
import com.example.diaryproject.dtos.requests.LoginDiaryRequest;
import com.example.diaryproject.dtos.responses.CreateDiaryResponse;
import com.example.diaryproject.dtos.responses.DeleteDiaryResponse;
import com.example.diaryproject.dtos.responses.LoginDiaryResponse;
import com.example.diaryproject.exceptions.DiaryDoesNotExistException;
import com.example.diaryproject.exceptions.InvalidEmailAddressException;
import com.example.diaryproject.exceptions.WrongPasswordException;
import com.example.diaryproject.utils.EmailValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DiaryServiceImplementationTest {
    private CreateDiaryRequest createDiaryRequest;
    DeleteDiaryRequest deleteDiaryRequest;
    private CreateDiaryResponse createDiaryResponse;
    @Autowired
    public DiaryService diaryService;

    @BeforeEach
    void setUp() {
        diaryService.deleteAll();
        createDiaryRequest = new CreateDiaryRequest();
    }

    @DisplayName("Created Diary is Not Empty Test -> ")
    @Test
    void createDiaryTest() throws InvalidEmailAddressException {
        createDiaryRequest = new CreateDiaryRequest();
        createDiaryRequest.setUsername("aiyeola");
        createDiaryRequest.setEmailAddress("aiyeola@gmail.com");
        createDiaryRequest.setPassword("password");
        createDiaryResponse = diaryService.createDiary(createDiaryRequest);
        assertNotNull(createDiaryResponse);
    }

    @DisplayName("When dairy is created repository increases-> ")
    @Test
    void repositoryIncreasesWhenDairyIsCreatedTest() throws InvalidEmailAddressException {
        createDiaryRequest = new CreateDiaryRequest();
        createDiaryRequest.setUsername("aiyeola");
        createDiaryRequest.setEmailAddress("aiyeola@gmail.com");
        createDiaryRequest.setPassword("password");
        createDiaryResponse = diaryService.createDiary(createDiaryRequest);
        assertNotNull(createDiaryResponse);
        assertEquals(1, diaryService.findAllDairy().size());
    }

    @DisplayName("LoggedIn Diary is Not Empty Test ->")
    @Test
    void loginDiaryTest() throws DiaryDoesNotExistException, WrongPasswordException, InvalidEmailAddressException {
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
    @Test
    void canCreateDiaryTest() throws InvalidEmailAddressException {
        createDiaryRequest = new CreateDiaryRequest();
        createDiaryRequest.setUsername("aiyeola");
        createDiaryRequest.setEmailAddress("aiyeola@gmail.com");
        createDiaryRequest.setPassword("password");
        createDiaryResponse = diaryService.createDiary(createDiaryRequest);
        assertNotNull(createDiaryResponse);
        assertEquals(1, diaryService.count());
    }

    @DisplayName("Created diary is Allowed To Add Entry -> ")
    @Test
    void createdDiaryCanAddEntryTest() throws InvalidEmailAddressException {
        createDiaryRequest = new CreateDiaryRequest();
        createDiaryRequest.setUsername("aiyeola");
        createDiaryRequest.setEmailAddress("aiyeola@gmail.com");
        createDiaryRequest.setPassword("password");
        createDiaryResponse = diaryService.createDiary(createDiaryRequest);
        assertEquals(1, diaryService.count());
    }
    @DisplayName("Gmail Address Cannot Contain Symbols ")
    @Test void gmailAddressCannotContainSymbolsTest() throws InvalidEmailAddressException {
        createDiaryRequest = new CreateDiaryRequest();
        createDiaryRequest.setUsername("aiyeola");
        createDiaryRequest.setPassword("password");
        String emailAddress = "username@domain.com";
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if (emailAddress.matches(regexPattern)) {
            assertTrue(EmailValidation.isValidEmail(emailAddress));
        } else {
            throw new InvalidEmailAddressException("Email address must not contain Special Symbols/ Characters");

        }
        assertFalse(EmailValidation.patternMatches(emailAddress, regexPattern));
    }
    @DisplayName("Mail Address Must Be Validated To Ensure That It Has The Top-Level Domain")
    @Test void mailAddressMustBeValidated()throws InvalidEmailAddressException {
            createDiaryRequest = new CreateDiaryRequest();
            createDiaryRequest.setUsername("aiyeola");
            createDiaryRequest.setPassword("password");
            createDiaryRequest.setEmailAddress("aiyeola.hbjbascjbjsdijibsdbjidbhsdcdsh");
            assertThrows(InvalidEmailAddressException.class, ()-> createDiaryResponse = diaryService.createDiary(createDiaryRequest));
    }
    @DisplayName("Diary Can Be Deleted Test")
    @Test void diaryCanBeDeletedTest() throws InvalidEmailAddressException, DiaryDoesNotExistException {
        createDiaryRequest = new CreateDiaryRequest();
        createDiaryRequest.setUsername("aiyeolababy");
        createDiaryRequest.setEmailAddress("aiyeolasulty@gmail.com");
        createDiaryRequest.setPassword("password");
        createDiaryResponse = diaryService.createDiary(createDiaryRequest);
        assertEquals(1, diaryService.count());
        deleteDiaryRequest = new DeleteDiaryRequest();
        deleteDiaryRequest.setId(createDiaryRequest.getId());
        deleteDiaryRequest.setUsername(createDiaryRequest.getUsername());
        DeleteDiaryResponse deleteDiaryResponse = diaryService.deleteDiary(deleteDiaryRequest);
        assertEquals(0,diaryService.count());
    }
}