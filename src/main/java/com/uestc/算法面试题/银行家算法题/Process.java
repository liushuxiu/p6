package com.uestc.算法面试题.银行家算法题;

public class Process extends Thread{
    private String name;
    private Resources resources;
    //需要各类资源的最大值
    private int maxA;
    private int maxB;
    private int maxC;
    //已分配各类资源个数
    private int alloA;
    private int alloB;
    private int alloC;
    //需要各资源的个数
    private int needA;
    private int needB;
    private int needC;
    //构造方法
    public Process(int maxA, int maxB, int maxC, int alloA, int alloB, int alloC,Resources resources, String name) {
        this.maxA = maxA;
        this.maxB = maxB;
        this.maxC = maxC;
        this.alloA = alloA;
        this.alloB = alloB;
        this.alloC = alloC;
        needA=maxA-alloA;
        needB=maxB-alloB;
        needC=maxC-alloC;
        this.resources=resources;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + "需要资源个数分别为：A:"+needA+",B:"+needB+",C:"+needC);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true){
            if(resources.getA()>=needA&&resources.getB()>=needB&&resources.getC()>=needC){
                System.out.println(name + "执行完毕，归还资源");
                resources.update(alloA,alloB,alloC);
                break;
            }
        }
    }
}