package com.uestc.config;


import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private User user;

    public void test() {
        getUserName(10);
    }

    public void getUserName(int add) {

        System.out.println(user.getName() + (user.getAge() + add) + "岁");
    }
}
