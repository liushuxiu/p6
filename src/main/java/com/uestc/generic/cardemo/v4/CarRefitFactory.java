package com.uestc.generic.cardemo.v4;

public class CarRefitFactory {
    public static Runnable turnTo(Class<?> targetClass, Runnable srcCar) {
        Runnable targetCar = null;
        Object o;
        try {
            o = targetClass.newInstance();
            if( o instanceof Runnable){
                targetCar = (Runnable)o;

                //执行改装过程...。例如获取源轿车(srcCar)的属性并赋值给新的车(targetCar)，具体过程省略，非重点。
            }
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return targetCar;
    }

    public static void main(String[] args) {
        /** 演示使用 */
        Runnable runable = CarRefitFactory.turnTo(Buick.class, new Ford());
        Buick newBuick = (Buick)runable;
        newBuick.autoRun();

        Ford newFord = (Ford)CarRefitFactory.turnTo(Ford.class, new Buick());
        newFord.fly();
    }
}