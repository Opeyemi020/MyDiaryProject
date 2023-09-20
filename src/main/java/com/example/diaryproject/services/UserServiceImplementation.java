package com.example.diaryproject.services;
import com.example.diaryproject.data.models.User;
import com.example.diaryproject.data.repository.UserRepository;
import com.example.diaryproject.dtos.requests.LoginUserRequest;
import com.example.diaryproject.dtos.requests.RegisterUserRequest;
import com.example.diaryproject.dtos.responses.LoginUserResponse;
import com.example.diaryproject.dtos.responses.RegisterUserResponse;
import com.example.diaryproject.exceptions.UserDoesNotExistException;
import com.example.diaryproject.exceptions.DiaryUsernameAlreadyExistExceptions;
import com.example.diaryproject.exceptions.WrongPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static com.example.diaryproject.utils.Mapper.map;
@RequiredArgsConstructor
@Service
public class UserServiceImplementation implements UserService {
    private UserRepository userRepository;
    private boolean isLoggedIn;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) {
        validateDuplicateUsername(registerUserRequest);
        User user = map(registerUserRequest);
        userRepository.save(user);
        return map(user);
    }

    private void validateDuplicateUsername(RegisterUserRequest registerUserRequest) {
        boolean check = confirmUserName(registerUserRequest);
        if (check)
            throw new DiaryUsernameAlreadyExistExceptions("username already exist, kindly enter a valid username");
    }

    private boolean confirmUserName(RegisterUserRequest registerUserRequest) {
        User user = userRepository.findByUsername(registerUserRequest.getUsername());
        return user != null;
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public LoginUserResponse login(LoginUserRequest loginUserRequest) throws UserDoesNotExistException, WrongPasswordException {
        User foundUser = userRepository.findByUsername(loginUserRequest.getUsername());
        if (foundUser == null) throw new UserDoesNotExistException("User does not exist");
        if (!Objects.equals(foundUser.getPassword(), loginUserRequest.getPassword())) {
            throw new WrongPasswordException("Wrong password , please Enter a correct password");
        } else if (foundUser.getPassword().equals(loginUserRequest.getPassword())) {
            isLoggedIn = true;
            LoginUserResponse loginUserResponse = new LoginUserResponse();
            loginUserResponse.setId((foundUser.getId()));
            String pattern = "hh:mm:ss a dd-mm-yyyy";
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern);
            loginUserResponse.setMessage("Login successfully at" + fmt.format(foundUser.getDateTime()));

            return loginUserResponse;
        }
        return null;
    }

    @Override
    public boolean logout() {
        return isLoggedIn;
    }

public boolean isLoggedIn(){
        return isLoggedIn = true;
    }
}
