package com.demo.service;

public interface SecurityService {
    String usernameIfLogged();

    void login(String username, String password);
}