package com.example.dmrken.muhttpupload.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dmrken on 2018/7/25.
 */

public enum RequestExecutor {
    //enum枚举，全局单例
          INSTANCE;
    private  ExecutorService eExecutorservice;

    RequestExecutor() {
        eExecutorservice = Executors.newSingleThreadExecutor();
    }

    //执行请求
    public void exector(){

    }
}
