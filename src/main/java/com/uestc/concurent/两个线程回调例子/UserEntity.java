package com.uestc.concurent.两个线程回调例子;

import lombok.Data;

/**
 * 用户实体
 */

@Data
public class UserEntity {
    /**
     * 用户 Id
     */
    public int userId;

    public int age;

    /**
     * 用户名称
     */
    public String userName;

    /**
     * 密码
     */
    public String password;

    /**
     * 英雄形象
     */
    public String heroAvatar;

    public String getUserName() {
        int res = 0;
        for (int i = 0; i < 100000000; i++) {
            res++;
        }
        return userName+ res;
    }
}
