package com.uestc.算法面试题;

public class Resources {
    //可用各资源数量
    private int A;
    private int B;
    private int C;

    public Resources(int a, int b, int c) {
        A = a;
        B = b;
        C = c;
    }

    public int getA() {
        return A;
    }
    public void setA(int a) {
        A = a;
    }
    public int getB() {
        return B;
    }
    public void setB(int b) {
        B = b;
    }
    public int getC() {
        return C;
    }
    public void setC(int c) {
        C = c;
    }

    public synchronized void update(int a,int b,int c){
        setA(this.A+a);
        setB(this.B+b);
        setC(this.C+c);
        //System.out.println("当前可用资源数：A："+A+",B:"+B+",C:"+C);
    }
}
