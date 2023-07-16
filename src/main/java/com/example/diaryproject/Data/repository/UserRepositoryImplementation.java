package com.example.diaryproject.Data.repository;


import com.example.diaryproject.Data.models.User;

import java.util.ArrayList;
import java.util.Objects;

public class UserRepositoryImplementation implements UserRepository{

    private int count = 0;
    ArrayList<User> users = new ArrayList<>();
    @Override
    public void save(User user) {
        user.setId(generateId());
       users.add(user);
       count+=1;
    }
    private int generateId() {
        return count;
    }

    @Override
    public int count() {
        return count;
    }
    public User findBy(int id){
        for (User user : users) {
            if (user.getId() == id)return user;
        }return null;
    }
    public User findByUsername(String username){
        for (User user : users) {
            if (Objects.equals(user.getUsername(), username)) return user;
        }
        return  null;
    }

    @Override
    public void deleteBy(String username) {
        for (User user: users) {
            if (Objects.equals(user.getUsername(), username)){users.remove(user);count-=1;
                break;}
        }
    }
    public void deleteById(int id){
        for (User user: users) {
            if (user.getId() == id) {users.remove(user);count-=1;
                break;}}}
}
