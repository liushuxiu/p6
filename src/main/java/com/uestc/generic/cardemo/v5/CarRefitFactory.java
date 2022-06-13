package com.uestc.generic.cardemo.v5;

public class CarRefitFactory {
    public static <E extends Runnable> E turnTo(Class<E> targetClass, Runnable srcCar) {
        E targetCar = null;
        try {
            targetCar = targetClass.newInstance();

            //执行改装过程...。例如获取源轿车(srcCar)的属性并赋值给新的车(targetCar)，具体过程省略，非重点。
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
        Buick newBuick = CarRefitFactory.turnTo(Buick.class, new Ford());
        newBuick.autoRun();

        Ford newFord = CarRefitFactory.turnTo(Ford.class, new Buick());
        newFord.fly();
    }
}