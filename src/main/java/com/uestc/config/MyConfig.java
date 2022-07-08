package com.uestc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public User user(){
        User user = new User();
        user.setId(1);
        user.setName("刘炎");
        user.setAge(32);
        return user;
    }
}
