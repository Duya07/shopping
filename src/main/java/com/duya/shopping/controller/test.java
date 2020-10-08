package com.duya.shopping.controller;

import com.duya.shopping.service.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {

    @Autowired
    private UserImpl user;

    @GetMapping("login")
    public String login(String username, String password) {
        if ("admin".equals(username) & "admin".equals(password)) {
            return "1";
        }
        return "username:" + username + ",password:" + password;
    }

    @GetMapping("registered")
    public String registered(String username, String password, String email, String mobile) {
        user.registered(username, password, email, mobile);
        return "ok";
    }
}
