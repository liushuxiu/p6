package com.uestc.generic.cardemo.v6;

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
}