package com.uestc.算法面试题;



public class 银行家算法 {


    public static void main(String[] args) {
        Resources resources=new Resources(3,3,2);
        Process p0=new Process(7,5,3,0,1,0,resources, "P0");
        Process p1=new Process(3,2,2,2,0,0,resources, "P1");
        Process p2=new Process(9,0,2,3,0,2,resources,"P2");
        Process p3=new Process(2,2,2,2,1,1,resources,"P3");
        Process p4=new Process(4,3,3,0,0,2,resources, "P4");
        p0.start();
        p1.start();
        p2.start();
        p3.start();
        p4.start();
    }

}
