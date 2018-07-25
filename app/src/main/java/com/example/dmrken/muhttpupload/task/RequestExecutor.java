package com.example.dmrken.muhttpupload.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dmrken on 2018/7/25.
 * 线程池
 */

public enum RequestExecutor {
    //enum枚举，全局单例
    INSTANCE;
    private ExecutorService eExecutorservice;

    RequestExecutor() {
        eExecutorservice = Executors.newSingleThreadExecutor();
    }

    /**
     * 执行一个请求
     *
     * @param request 请求对象
     */
    public void execute(Request request,HttpListener listener) {
        eExecutorservice.execute(new RequestTask(request,listener));//子线程中执行

    }
}
