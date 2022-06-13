package com.uestc.generic.cardemo.v4;

public class Ford implements Runnable {

    @Override
    public void run(){
        System.out.println("ford run");
    }

    public void fly(){
        System.out.println("ford fly");
    }
}