package com.example.diaryproject.services;

import com.example.diaryproject.dtos.requests.LoginUserRequest;
import com.example.diaryproject.dtos.requests.RegisterUserRequest;
import com.example.diaryproject.dtos.responses.LoginUserResponse;
import com.example.diaryproject.dtos.responses.RegisterUserResponse;
import com.example.diaryproject.exceptions.UserDoesNotExistException;
import com.example.diaryproject.exceptions.DiaryUsernameAlreadyExistExceptions;
import com.example.diaryproject.exceptions.WrongPasswordException;

public interface UserService {

    RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) throws DiaryUsernameAlreadyExistExceptions;

    long count();
    boolean isLoggedIn();
    LoginUserResponse login(LoginUserRequest loginUserRequest) throws UserDoesNotExistException, WrongPasswordException;

    boolean logout() throws UserDoesNotExistException;
}
