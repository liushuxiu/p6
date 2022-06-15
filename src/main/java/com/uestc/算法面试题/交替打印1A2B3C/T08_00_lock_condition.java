package com.uestc.算法面试题.交替打印1A2B3C;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T08_00_lock_condition {

    public static void main(String[] args) {

        char[] aI = "1234567".toCharArray();
        char[] aC = "ABCDEFG".toCharArray();

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {

            lock.lock();

            try {
                for (char c : aI) {
                    System.out.print(c);
                    condition.signal();  // notify()
                    condition.await(); // wait()
                }
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock(); // synchronized
            try {
                for (char c : aC) {
                    System.out.print(c);
                    condition.signal(); // o.notify
                    condition.await(); // o.wait
                }
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }
}

/*
代码表面看起来，创建锁，调用方法跟synchronized没有区别，但是关键点在于Condition这个类，大家应该知道生产者和消费者这个概念，生产者生产馒头，生产满了进入等待队列，消费者吃馒头，吃光了同样进入等待队列，如果我们使用传统的synchronized，当生产者生产满时，需要从等待队列中叫醒消费者，但调用notify方法时，我们能保证一定叫醒的是消费者吗？不能，这件事是无法做到的，那该怎么保证叫醒的一定是消费者呢？

有两种解决方案：

① 如果篮子已经满了，生产者会去等待队列中叫醒一个线程，但如果叫醒的线程还是一个生产者，那么新的生产者起来之后一定要先检查一下篮子是否满了，不能上来就生产，如果是满的，那接着去叫醒下一个线程，这样依次重复，我们一定会有一次叫醒的是消费者。

② notifyAll()方法：将等待队列中的生产者和消费者全唤醒，消费者发现篮子是满的，就去消费，生产者发现篮子是满的，就继续回到等待队列。
 */
