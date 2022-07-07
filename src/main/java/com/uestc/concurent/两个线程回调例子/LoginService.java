package com.uestc.concurent.两个线程回调例子;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

/**
 * 登陆服务
 */
public class LoginService {
    /**
     * 日志对象
     */
    static private final Logger LOGGER = LoggerFactory.getLogger(LoginService.class);

    /**
     * 单例对象
     */
    static private final LoginService _instance = new LoginService();

    /**
     * 私有化类默认构造器
     */
    private LoginService() {
    }

    /**
     * 获取单例对象
     *
     * @return 单例对象
     */
    static public LoginService getInstance() {
        return _instance;
    }

    /**
     * 用户登陆
     *
     * @param userName 用户名称
     * @param password 密码
     * @param callback 回调函数
     */
    public void userLogin(String userName, String password, Function<UserEntity, String> callback) {




        if (null == userName ||
            null == password) {
            return;
        }

        // 创建异步操纵
        AsyncGetUserByName asyncOp = new AsyncGetUserByName(userName, password) {
            @Override
            public void doFinish() {
                if (null != callback) {
                    // 执行回调函数
                    System.out.println(22);
                    callback.apply(this.getUserEntity());
                    System.out.println(44);
                }
            }
        };

        // 执行异步操纵
        AsyncOperationProcessor.getInstance().process(asyncOp);
    }

    public void userLogin2(String userName, String password, MyCallback callback) {

        if (null == userName ||
                null == password) {
            return;
        }

        // 创建异步操纵
        AsyncGetUserByName asyncOp = new AsyncGetUserByName(userName, password) {
            @Override
            public void doFinish() {
                if (null != callback) {
                    // 执行回调函数
                    System.out.println(22);
                    callback.doCallback(this.getUserEntity());
                    System.out.println(44);
                }
            }
        };

        // 执行异步操纵
        AsyncOperationProcessor.getInstance().process(asyncOp);
    }

    /**
     * 异步方式获取用户
     */
    private class AsyncGetUserByName implements IAsyncOperation {
        /**
         * 用户名称
         */
        private final String _userName;

        /**
         * 密码
         */
        private final String _password;

        /**
         * 用户实体
         */
        private UserEntity _userEntity = null;

        /**
         * 类参数构造器
         *
         * @param userName 用户名称
         * @param password 密码
         * @throws IllegalArgumentException if null == userName || null == password
         */
        AsyncGetUserByName(String userName, String password) {
            if (null == userName ||
                null == password) {
                throw new IllegalArgumentException();
            }

            _userName = userName;
            _password = password;
        }

        /**
         * 获取用户实体
         *
         * @return 用户实体
         */
        public UserEntity getUserEntity() {
            return _userEntity;
        }

        @Override
        public int getBindId() {
            return _userName.charAt(_userName.length() - 1);
        }

        @Override
        public void doAsync() {
            try {

                System.out.println("00");


            } catch (Exception ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
    }
}
