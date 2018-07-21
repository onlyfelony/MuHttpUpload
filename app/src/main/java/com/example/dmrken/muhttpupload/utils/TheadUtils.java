package com.example.dmrken.muhttpupload.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程封装类
 */

public class TheadUtils {

    private static ExecutorService sExecutorService = Executors.newSingleThreadExecutor();//单例线程池

    public static void execute(Runnable runnable) {
        sExecutorService.execute(runnable);//执行一个线程
    }
}
