package com.example.dmrken.muhttpupload.utils;

import android.os.Handler;
import android.os.Looper;


/**
 * Created by dmrken on 2018/7/21.
 */
/*发送消息Handler
 */
public class Poster extends Handler {

    private static Poster instance;//单例

    public static Poster getInstance() {
        if (instance == null)
            synchronized (Poster.class) {
                if (instance == null)
                    instance = new Poster();

            }
        return instance;
    }

    //为了防止外面通过new构造Poster,将构造方法设为private
    //在子线程中不能直接new主线程的Handler，用Looper.getMainLooper()将主线程Looper传给Handler
    private Poster() {
        //将main线程的looper传给这个handler,这样在子线程的任何地方都可以将消息发给主线程
        // 这个handler对象的handleMessage()中运行在它绑定的Looper的线程中
        super(Looper.getMainLooper());
    }


}
