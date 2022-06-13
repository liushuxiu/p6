package com.uestc.generic.cardemo.v6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarContainer<E extends Runnable> {
    private List<E> container = new ArrayList<E>();

    public void pop(Set<E> consumer){
        consumer.add(container.remove(container.size()));
    }

//    public void pop(Set<? super E> consumer){
//        consumer.add(container.remove(container.size()));
//    }

    public void add(E e) {
        container.add(e);
    }

    public void add(Driver<E> producer) {
        container.add(producer.getDrivingCar());
    }

//    public void add(Driver<? extends E> producer) {
//        container.add(producer.getDrivingCar());
//    }


    public static void main(String[] args) {
        CarContainer<Runnable> container = new CarContainer<Runnable>();
        Buick buick = new Buick();
        container.add(buick);

//        Driver<Buick> driver = new Driver<Buick>();
//        driver.drive(buick);
//        container.add(driver);

        Driver<Runnable> driver = new Driver<Runnable>();
        driver.drive(buick);
        container.add(driver);


        Set<Runnable> consumer = new HashSet<Runnable>();
        container.pop(consumer);

//        Set<Object> consumer2 = new HashSet<Object>();
//        container.pop(consumer2);
    }
}