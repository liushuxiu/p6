package com.uestc.算法面试题.交替打印1A2B3C;


import java.util.concurrent.CountDownLatch;

public class T07_00_sync_wait_notify {

    private static CountDownLatch latch = new CountDownLatch(1); // 设置门栓的参数为1，即只有一个门栓

    public static void main(String[] args) throws InterruptedException {

        final Object o = new Object();

        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        Thread t1 = new Thread(() -> {
            System.out.println("t1-0");
            latch.countDown(); // 门栓的数值-1，即打开门
            System.out.println("t1-1");
            System.out.println("t1-2");


            synchronized (o) {
                for (char c : aI) {
                    System.out.print(c);

                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }, "t1");


        Thread t2 = new Thread(() -> {
            try {
                System.out.println("t2-0");
                latch.await(); // 想哪个线程后执行，await()就放在哪个线程里
                System.out.println("t2-1");
                System.out.println("t2-2");

            } catch (Exception e) {
                e.printStackTrace();
            }
            synchronized (o) {
                for (char c : aC) {
                    System.out.print(c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }, "t2");


        t2.start();
        t1.start();

    }
}
