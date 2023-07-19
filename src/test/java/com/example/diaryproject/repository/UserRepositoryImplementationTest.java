package com.example.diaryproject.repository;

import com.example.diaryproject.Data.models.User;
import com.example.diaryproject.Data.repository.UserRepository;
import com.example.diaryproject.Data.repository.UserRepositoryImplementation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryImplementationTest {
    @Test public void user_Exist_Test(){
        UserRepository userRepository = new UserRepositoryImplementation();
        assertNotNull(userRepository);
    }
    @Test public void user_Is_Saved_Test(){
        UserRepository userRepository = new UserRepositoryImplementation();
     userRepository.save(new User());
     assertEquals(1,userRepository.count());
    }
    @Test public void can_Find_User_By_Id_Test(){
        UserRepository userRepository = new UserRepositoryImplementation();
        User user = new User();
        User user1 = new User();
        userRepository.save(user);
        userRepository.save(user1);
        assertEquals(2,userRepository.count());
        userRepository.findBy(user.getId());
        assertEquals(user,userRepository.findBy(user.getId()));
    }
    @Test public void can_Delete_By_Id_Test()  {
        UserRepository userRepository = new UserRepositoryImplementation();
        User user = new User();
        User user1 = new User();
        userRepository.save(user);
        userRepository.save(user1);
        assertEquals(2,userRepository.count());
        userRepository.deleteById(user.getId());
        assertEquals(1,userRepository.count());
    }
    @Test public void can_Find_By_Username_Test(){
        UserRepository userRepository = new UserRepositoryImplementation();
        User user = new User();
        User user1 = new User();
        userRepository.save(user);
        userRepository.save(user1);
        userRepository.findByUsername(user.getUsername());
        assertEquals(user,userRepository.findByUsername(user.getUsername()));
    }
    @Test public void can_Delete_By_Username_Test() {
        UserRepository userRepository = new UserRepositoryImplementation();
        User user = new User();
        User user1 = new User();
        userRepository.save(user);
        userRepository.save(user1);
        assertEquals(2, userRepository.count());
        String username = user.getUsername();
        userRepository.deleteBy(username);
        assertEquals(1, userRepository.count());
    }
}