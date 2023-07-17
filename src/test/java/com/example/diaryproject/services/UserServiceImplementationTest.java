package com.example.diaryproject.services;

import com.example.diaryproject.dtos.requests.LoginUserRequest;
import com.example.diaryproject.dtos.requests.RegisterUserRequest;
import com.example.diaryproject.dtos.responses.LoginUserResponse;
import com.example.diaryproject.dtos.responses.RegisterUserResponse;
import com.example.diaryproject.exceptions.UserDoesNotExistException;
import com.example.diaryproject.exceptions.UsernameAlreadyExistExceptions;
import com.example.diaryproject.exceptions.WrongPasswordException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplementationTest {
    LoginUserRequest loginUserRequest;
    private RegisterUserRequest registerUserRequest;

    private final UserService userService = new UserServiceImplementation();
    private RegisterUserResponse registerUserResponse;

    @BeforeEach
    void startWith() {
        registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("Esther");
        registerUserRequest.setEmailAddress("aiyeola@gmail.com");
        registerUserRequest.setPassword("password");
    }

    @DisplayName("Register User Test -> ")
    @Test void registerUserTest() {
        registerUserResponse = userService.registerUser(registerUserRequest);
        Assertions.assertNotNull(registerUserResponse);
    }

    @DisplayName("Login User Test -> ")
    @Test void loginUserTest() throws UserDoesNotExistException, WrongPasswordException {
        registerUserResponse = userService.registerUser(registerUserRequest);
        loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("Esther");
        loginUserRequest.setPassword("password");
        LoginUserResponse loginUserResponse = userService.login(loginUserRequest);
        Assertions.assertNotNull(loginUserResponse);
    }

    @DisplayName("User Can Register Test -> ")
    @Test
    void UserCanRegisterTest() {
        registerUserResponse = userService.registerUser(registerUserRequest);
        Assertions.assertEquals(1, userService.count());
    }

    @DisplayName("Multiple User Can Register Test -> ")
    @Test void multipleUserCanRegisterTest() {
        registerUserResponse = userService.registerUser(registerUserRequest);
        registerUserRequest.setUsername("Oluseyi");
        registerUserRequest.setEmailAddress("aiyeola@gmail.com");
        registerUserRequest.setPassword("password");
        registerUserResponse = userService.registerUser(registerUserRequest);
        Assertions.assertEquals(2, userService.count());
    }

    @DisplayName("Registered User Can Login Test -> ")
    @Test
    void registeredUserCanLoginTest() throws UserDoesNotExistException, WrongPasswordException {
        registerUserResponse = userService.registerUser(registerUserRequest);
        assertFalse(userService.isLoggedIn());
        loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("Esther");
        loginUserRequest.setPassword("password");
        userService.login(loginUserRequest);
        assertTrue(userService.isLoggedIn());
    }

    @DisplayName("registered User That's LoggedIn Can Logout Test ->")
    @Test
    void registeredUserThatIsLoggedInCanLogoutTest() throws UserDoesNotExistException {
        registerUserResponse = userService.registerUser(registerUserRequest);
        assertFalse(userService.isLoggedIn());
        loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("Esther");
        loginUserRequest.setPassword("password");
        userService.logout();
        assertFalse(userService.isLoggedIn());
    }

    @DisplayName("user Cannot Register With An Existing Username ->")
    @Test
    void userCannotRegisterWithAnExistingUsername() {
        registerUserResponse = userService.registerUser(registerUserRequest);
        RegisterUserRequest registerUserRequest1 = new RegisterUserRequest();
        registerUserRequest1.setUsername("Esther");
        registerUserRequest1.setEmailAddress("aiyeola@gmail.com");
        registerUserRequest1.setPassword("password");
        assertThrows(UsernameAlreadyExistExceptions.class,
                () -> userService.registerUser(registerUserRequest1));
        try {
            userService.registerUser(registerUserRequest1);
        } catch (Exception exception) {
            assertEquals(UsernameAlreadyExistExceptions.class, exception.getClass());
        }
    }

    @DisplayName(" user Cannot Login Without Correct Password ->")
    @Test void userCannotLoginWithoutCorrectPassword() throws UserDoesNotExistException, WrongPasswordException {
        registerUserResponse = userService.registerUser(registerUserRequest);
        assertFalse(userService.isLoggedIn());
        loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("Esther");
        loginUserRequest.setPassword("incorrect password");
        assertThrows(WrongPasswordException.class,
                ()-> userService.login(loginUserRequest));
        try {
            userService.login(loginUserRequest);
        }catch (Exception exception){
            assertEquals(WrongPasswordException.class, exception.getClass());
        }
    }
    @DisplayName("User Can Login Only When Password Is Correct -> ")
    @Test void userCanLoginOnlyWhenPasswordIsCorrect() {
        registerUserResponse = userService.registerUser(registerUserRequest);
        assertFalse(userService.isLoggedIn());
        loginUserRequest = new LoginUserRequest();
        loginUserRequest.setUsername("Esther");
        loginUserRequest.setPassword("incorrect password");
        assertThrows(WrongPasswordException.class,
                () -> userService.login(loginUserRequest));
        try {
            userService.login(loginUserRequest);
        } catch (Exception exception) {
            assertEquals(WrongPasswordException.class, exception.getClass());
        }
    }
}