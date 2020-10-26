package com.duya.shopping.controller;

import com.alibaba.fastjson.JSON;
import com.duya.shopping.service.UserImpl;
import com.duya.shopping.utils.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {

    @Autowired
    private UserImpl user;

    @GetMapping("login")
    public String login() {
        ServiceResult sr = ServiceResult.success("login接口成功了兄弟");
        return JSON.toJSONString(sr);
    }

    @GetMapping("registered")
    public String registered(String username, String password, String email, String mobile) {
        user.registered(username, password, email, mobile);
        return "ok";
    }
}
