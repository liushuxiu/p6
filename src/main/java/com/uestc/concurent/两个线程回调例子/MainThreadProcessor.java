package com.uestc.concurent.两个线程回调例子;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 主线程处理器
 */
public final class MainThreadProcessor {

    /**
     * 单例对象
     */
    static private final MainThreadProcessor _instance = new MainThreadProcessor();

    /**
     * 创建一个单线程
     */
    private final ExecutorService _es = Executors.newSingleThreadExecutor((newRunnable) -> {
        Thread newThread = new Thread(newRunnable);
        newThread.setName("MainThreadProcessor");
        return newThread;
    });

    /**
     * 私有化类默认构造器
     */
    private MainThreadProcessor() {
    }

    /**
     * 获取单例对象
     *
     * @return 单例对象
     */
    static public MainThreadProcessor getInstance() {
        return _instance;
    }


    /**
     * 处理消息对象
     *
     * @param r Runnable
     */
    public void process(Runnable r) {
        if (null != r) {
            _es.submit(r);
        }
    }


}
