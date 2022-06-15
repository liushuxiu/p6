package com.uestc.generic.calendardemo;

import java.util.GregorianCalendar;

class Demo1<T extends Comparable<T>> {


}


 class Demo2<T extends Comparable<? super  T>> {


}


public class CalendarDemo<T extends Comparable<? super  T>> {
   // GregorianCalendar 的父类Calendar实现了comparable接口，所以要用 ? super T
//    Demo1<GregorianCalendar> p1 = null;

    Demo2<GregorianCalendar> p2 = null;

}
