package com.kata.bootstrap.service;

import com.kata.bootstrap.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUser(long id);

    User getUserByUsername(String username);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(long id);
}
