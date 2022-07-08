package com.uestc.concurent;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
//        int n =1;
//        int x = n << 1;
//        System.out.println(x);
//        System.out.println(n);

        Integer i = resizeStamp(16);
        System.out.println(i + "---------" + Integer.toBinaryString(i).length());

//        Integer xx = 1299;
//        Integer yy = xx;
//        yy = null;
//        System.out.println(xx);
//        map.put(1,1);
//        map.put(0, 0);
//        System.out.println(map.size());
    }

    private static int resizeStamp(int n) {
        return Integer.numberOfLeadingZeros(n) | (1 << (16 - 1));
    }
}
