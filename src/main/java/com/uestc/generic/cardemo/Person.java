package com.uestc.generic.cardemo;

import lombok.Data;

@Data
class Cat <T> {
    T age;


}

public class Person {

    void add (Cat<? extends Number> cat) {
        Number age = cat.getAge();
//        cat.setAge(age);   使用到了Number属于消费者, 返回Number属于生产者

        //PECS
    }


}
