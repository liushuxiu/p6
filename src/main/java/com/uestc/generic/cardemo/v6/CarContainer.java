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

    public void add0(E e) {
        container.add(e);
    }

    public void add1(Driver<E> producer) {
        container.add(producer.getDrivingCar());
    }

    public void add2(Driver<? extends E> producer) {
        container.add(producer.getDrivingCar());
    }


    public static void main(String[] args) {
        CarContainer<Runnable> container = new CarContainer<Runnable>();
        Buick buick = new Buick();
        container.add0(buick);

        Driver<Runnable> driver1 = new Driver<Runnable>();
        driver1.drive(buick);
        container.add1(driver1);

        Driver<Buick> driver2 = new Driver<Buick>();
        driver2.drive(buick);
        container.add2(driver2);




        Set<Runnable> consumer = new HashSet<Runnable>();
        container.pop(consumer);

//        Set<Object> consumer2 = new HashSet<Object>();
//        container.pop(consumer2);
    }
}