package com.demo.service;


import com.demo.model.User;

public interface UserService {
    void create(User user);

    User get(String username);
}
