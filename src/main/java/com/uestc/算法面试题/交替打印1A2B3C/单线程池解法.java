package com.uestc.算法面试题.交替打印1A2B3C;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class 单线程池解法 {

    static ExecutorService singleThread = Executors.newSingleThreadExecutor();

    static volatile int index = 0;

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            char[] nums = "1234567".toCharArray();
            for (char num : nums) {
                while (index != 0) {
//                        System.out.println(Thread.currentThread().getName() + "还没轮到我");

                }
                singleThread.submit(() -> {
                    System.out.print(num);
                });
                index = 1;
            }

        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                char[] chars = "ABCDEFG".toCharArray();
                for (char c : chars) {
                    while (index != 1) {
//                        System.out.println(Thread.currentThread().getName() + "还没轮到我");
                    }
                    singleThread.submit(() -> {
                        System.out.print(c);
                    });
                    index = 0;

                }

            }
        });

        t2.start();
        t1.start();

    }



}
