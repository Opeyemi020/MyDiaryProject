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
import com.example.diaryproject.services.diaryService.DiaryService;
import com.example.diaryproject.utils.EmailValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.mockito.BDDMockito.given;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DiaryServiceImplementationTest {
    private CreateDiaryRequest createDiaryRequest;
    DeleteDiaryRequest deleteDiaryRequest;
    DeleteDiaryResponse deleteDiaryResponse;
    private CreateDiaryResponse createDiaryResponse;
    LoginDiaryResponse loginDiaryResponse;
    @Autowired
    public DiaryService diaryService;

    @BeforeEach
    void setUp() {
        diaryService.deleteAll();
        createDiaryRequest = new CreateDiaryRequest();
    }

    @DisplayName("Created Diary is Not Empty Test -> ")
    @Test
    void createDiaryTest()  {
        createDiaryRequest = new CreateDiaryRequest();
        createDiaryRequest.setUsername("aiyeola");
        createDiaryRequest.setEmailAddress("aiyeola@gmail.com");
        createDiaryRequest.setPassword("password");
        given(diaryService.createDiary(createDiaryRequest)).willReturn(createDiaryResponse);
        assertNotNull(createDiaryResponse);
    }

    @DisplayName("When dairy is created repository increases-> ")
    @Test
    void repositoryIncreasesWhenDairyIsCreatedTest() {
        createDiaryRequest = new CreateDiaryRequest();
        createDiaryRequest.setUsername("aiyeola");
        createDiaryRequest.setEmailAddress("aiyeola@gmail.com");
        createDiaryRequest.setPassword("password");
        given(diaryService.createDiary(createDiaryRequest)).willReturn(createDiaryResponse);
        assertNotNull(createDiaryResponse);
        assertEquals(1, diaryService.findAllDairy().size());
    }

    @DisplayName("LoggedIn Diary is Not Empty Test ->")
    @Test
    void loginDiaryTest() throws DiaryDoesNotExistException, WrongPasswordException {
        createDiaryRequest = new CreateDiaryRequest();
        createDiaryRequest.setUsername("aiyeola");
        createDiaryRequest.setEmailAddress("aiyeola@gmail.com");
        createDiaryRequest.setPassword("password");
        createDiaryResponse = diaryService.createDiary(createDiaryRequest);
        assertEquals(1, diaryService.findAllDairy().size());
        LoginDiaryRequest loginDiaryRequest = new LoginDiaryRequest();
        loginDiaryRequest.setUsername(createDiaryRequest.getUsername());
        loginDiaryRequest.setPassword(createDiaryRequest.getPassword());
        given(diaryService.loginDiary(loginDiaryRequest)).willReturn(loginDiaryResponse);
        assertNotNull(loginDiaryResponse);
    }

    @DisplayName("Diary Can Be Created Test ->")
    @Test
    void canCreateDiaryTest()  {
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
    void createdDiaryCanAddEntryTest(){
        createDiaryRequest = new CreateDiaryRequest();
        createDiaryRequest.setUsername("aiyeola");
        createDiaryRequest.setEmailAddress("aiyeola@gmail.com");
        createDiaryRequest.setPassword("password");
        given(diaryService.createDiary(createDiaryRequest)).willReturn(createDiaryResponse);
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
    @Test void mailAddressMustBeValidated(){
            createDiaryRequest = new CreateDiaryRequest();
            createDiaryRequest.setUsername("aiyeola");
            createDiaryRequest.setPassword("password");
            createDiaryRequest.setEmailAddress("aiyeola.hbjbascjbjsdijibsdbjidbhsdcdsh");
            assertThrows(InvalidEmailAddressException.class, ()-> given(diaryService.createDiary(createDiaryRequest)).willReturn(createDiaryResponse));
    }
    @DisplayName("Diary Can Be Deleted Test")
    @Test void diaryCanBeDeletedTest() throws DiaryDoesNotExistException {
        createDiaryRequest = new CreateDiaryRequest();
        createDiaryRequest.setUsername("aiyeolababy");
        createDiaryRequest.setEmailAddress("aiyeolasulty@gmail.com");
        createDiaryRequest.setPassword("password");
        given(diaryService.createDiary(createDiaryRequest)).willReturn(createDiaryResponse);
        assertEquals(1, diaryService.count());
        deleteDiaryRequest = new DeleteDiaryRequest();
        deleteDiaryRequest.setDiaryId(String.valueOf(createDiaryRequest.getId()));
        deleteDiaryRequest.setUsername(createDiaryRequest.getUsername());
        given(diaryService.deleteDiary(deleteDiaryRequest)).willReturn(deleteDiaryResponse);
        assertEquals(0,diaryService.count());
        assertNotNull(deleteDiaryResponse);
    }
}