package com.uestc.算法面试题.交替打印1A2B3C;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    static Thread t1 = null, t2 = null;

    static char[] nums = "1234567".toCharArray();
    static char[] chars = "ABCDEFG".toCharArray();

    public static void main(String[] args) throws InterruptedException {

        solution1();


    }

    private static void solution1() {
        t1 = new Thread(() -> {
            for (char c : nums) {
                System.out.print(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }, "t1");

        t2 = new Thread(() -> {
            for (char c : chars) {
                LockSupport.park();
                System.out.print(c);
                LockSupport.unpark(t1);
            }
            System.out.println();
        }, "t2");

        t1.start();
        t2.start();
    }


}