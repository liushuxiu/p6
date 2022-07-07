package com.uestc.算法面试题.交替打印1A2B3C;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionApplication2 {
    static char[] nums = "1234567".toCharArray();
    static char[] chars = "ABCDEFG".toCharArray();

        private static Runnable getThreadA(final ConditionService2 service) {
        return new Runnable() {
            @Override
            public void run() {
                for (char num : nums) {
                    service.excuteA(num);
                }

            }
        };
    }

        private static Runnable getThreadB(final ConditionService2 service) {
        return new Runnable() {
            @Override
            public void run() {
                for (char c : chars) {
                    service.excuteB(c);
                }
            }
        };
    }



        public static void main(String[] args) throws InterruptedException{
        ConditionService2 service = new ConditionService2();
        Runnable A = getThreadA(service);
        Runnable B = getThreadB(service);

        new Thread(A, "A").start();
        new Thread(B, "B").start();
    }

}



class ConditionService2 {

    // 通过nextThread控制下一个执行的线程
    private static int nextThread = 1;
    private ReentrantLock lock = new ReentrantLock();
    // 有三个线程，所以注册三个Condition
    Condition conditionA = lock.newCondition();
    Condition conditionB = lock.newCondition();

    public void excuteA(char num) {
        try {
            lock.lock();
            while (nextThread != 1) {
                conditionA.await();
            }
            System.out.print(num);
//            System.out.println(Thread.currentThread().getName() + " 工作");
            nextThread = 2;
            conditionB.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void excuteB(char c) {
        try {
            lock.lock();
            while (nextThread != 2) {
                conditionB.await();
            }
            System.out.print(c);
//            System.out.println(Thread.currentThread().getName() + " 工作");
            nextThread = 1;
            conditionA.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }



}

