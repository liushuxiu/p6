package com.uestc.generic.cardemo.v4;

public class Driver<T extends Runnable> {

    private T car;

    public void drive(T car){
        this.car = car;
        System.out.println("I am driving a " + car);
        car.run();
    }

    public T getDrivingCar(){
        return car;
    }

    public static void main(String[] args) {
        Driver<Ford> driver1 = new Driver<Ford>();
        driver1.drive(new Ford());
        Driver<Buick> driver2 = new Driver<Buick>();
        driver2.drive(new Buick());

        //...执行其它业务逻辑

        /** 获取司机1开的福特车执行fly方法 */
        driver1.getDrivingCar().fly();

        /** 获取司机2开的别克车执行autoRun方法 */
        driver2.getDrivingCar().autoRun();

    }
}