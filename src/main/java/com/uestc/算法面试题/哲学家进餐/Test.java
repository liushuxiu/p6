package com.uestc.算法面试题.哲学家进餐;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 哲学家就餐问题——死锁
 */
@Slf4j(topic = "c.test")
public class Test {
    public static void main(String[] args) {
        Chopstick c1 = new Chopstick("1");
        Chopstick c2 = new Chopstick("2");
        Chopstick c3 = new Chopstick("3");
        Chopstick c4 = new Chopstick("4");
        Chopstick c5 = new Chopstick("5");

        new Philosopher("苏格拉底",1,c1,c2).start();
        new Philosopher("柏拉图",2,c2,c3).start();
        new Philosopher("亚里士多德",3,c3,c4).start();
        new Philosopher("赫拉克利特",4,c4,c5).start();
        new Philosopher("阿基米德",5,c5,c1).start();
    }

}

/**
 * 哲学家类
 */
@Slf4j(topic = "c.Philosopher")
class Philosopher extends Thread{
    private Chopstick left;
    private Chopstick right;
    private int index;
    public Philosopher(String name, int index , Chopstick left, Chopstick right){
        super(name);
        this.index = index;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (true) {

            boolean eated = false ;
            // 奇偶分组，奇数先左右，偶数先右手。
            if(index %2 == 0){
                // 尝试获取左手的筷子
                synchronized (left){
                    SleepHelper.sleepSeconds(1 + index);
                    log.debug(left.toString() + "左");
                    // 尝试获取右手的筷子
                    synchronized (right){
                        log.debug(left.toString() + "右");
                        eat();
                        eated = true;
                    }
                }
            }else {
                // 尝试获取右手的筷子
                synchronized (right){
                    log.debug(left.toString() + "右");
                    // 尝试获取左手的筷子
                    synchronized (left){
                        log.debug(left.toString() + "左");
                        eat();
                        eated = true;
                    }
                }
            }

            if (eated) {
                break;
            }

        }
    }

    private void eat(){
        log.debug(Thread.currentThread().getName() + "eating...");
        SleepHelper.sleepSeconds(1);
    }

}



/**
 * 筷子类
 */
class Chopstick{
    String name;
    public Chopstick(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "筷子{" + name + '}';
    }
}

/**
 * 睡眠类
 */
class SleepHelper{

    public static void sleepSeconds(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
