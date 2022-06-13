package com.uestc.generic.cardemo.v1;

import com.uestc.generic.cardemo.Buick;
import com.uestc.generic.cardemo.Ford;

public class Driver {

    private Object car;

    public void drive(Object car){
        this.car = car;
        if(car instanceof Buick){
            System.out.println("I am driving a " + car);
            ((Buick)car).run();
        }else if(car instanceof Ford){
            System.out.println("I am driving a " + car);
            ((Ford)car).run();
        }
    }

    public Object getDrivingCar(){
        return car;
    }

    public static void main(String[] args) {
        Driver driver1 = new Driver();
        driver1.drive(new Ford());
        Driver driver2 = new Driver();
        driver2.drive(new Buick());

        //...执行其它业务逻辑

        /** 获取司机1开的福特车执行fly方法 */
        ((Ford)driver1.getDrivingCar()).fly();

        /** 获取司机2开的别克车执行autoRun方法 */
        ((Buick)driver2.getDrivingCar()).autoRun();

    }
}