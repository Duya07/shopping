package com.duya.shopping.service;

import com.duya.shopping.dao.UserDao;
import com.duya.shopping.pojo.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class UserImpl {

    @Autowired
    private UserDao userDao;

    // 注册的时候datajoined 和 lastlogin 时间是一样的
    // Firstname 和 username 是一样的
    public void registered(String username, String password, String email, String mobile){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(password);
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        userEntity.setDataJoined(timestamp);
        userEntity.setLastLogin(timestamp);
        userEntity.setFirstName(username);
        userEntity.setEmail(email);
        userEntity.setMobile(mobile);
        userDao.save(userEntity);
    }
}
