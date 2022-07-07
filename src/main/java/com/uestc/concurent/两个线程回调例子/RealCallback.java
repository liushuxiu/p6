package com.uestc.concurent.两个线程回调例子;

public class RealCallback implements MyCallback{
    @Override
    public void doCallback(UserEntity userEntity) {
        System.out.println(33);

        return ;
    }
}
