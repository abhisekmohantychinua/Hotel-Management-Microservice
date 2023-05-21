package dev.abhisek.userservice.service;

import dev.abhisek.userservice.entities.User;

import java.util.List;

public interface UserService {
    //create user
    User createUser(User user);
    //get a single user by userId
    User getUser(String userId);
    //get all the users
    List<User> getAllUser();
}
