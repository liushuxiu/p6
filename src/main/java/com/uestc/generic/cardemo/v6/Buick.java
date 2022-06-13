package com.uestc.generic.cardemo.v6;

public class Buick implements Runnable {

    @Override
    public void run(){
        System.out.println("buick run");
    }

    public void autoRun(){
        System.out.println("buick auto-run");
    }
}