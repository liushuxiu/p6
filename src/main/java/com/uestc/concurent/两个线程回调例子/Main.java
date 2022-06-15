package com.uestc.concurent.两个线程回调例子;

public class Main {

    public static void main(String[] args) {
//        LoginService.getInstance().userLogin("userName", "password", (userEntity) -> {
//
//            System.out.println(33);
//
//            return "1";
//        });

        LoginService.getInstance().userLogin2("userName", "password", new  RealCallback());



        System.out.println(66);

    }

}