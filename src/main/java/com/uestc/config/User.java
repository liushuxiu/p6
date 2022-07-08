package com.uestc.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class User {
    private int id;
    private String name;
    private int age;
}
