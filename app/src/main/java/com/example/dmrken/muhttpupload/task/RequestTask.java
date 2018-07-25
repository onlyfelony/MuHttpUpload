package com.example.dmrken.muhttpupload.task;

import com.example.dmrken.muhttpupload.utils.Logger;
import com.example.dmrken.muhttpupload.utils.Poster;

/**
 * Created by dmrken on 2018/7/25.
 * 执行请求的线程
 */

public class RequestTask implements Runnable {
    private Request request;
    private HttpListener listener;
    public RequestTask(Request request,HttpListener listener) {
        this.request = request;
        this.listener = listener;
    }


    @Override
    public void run() {
        //执行请求
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Logger.i("执行请求：" + request.toString());

        //----------------拿到响应
        Response response = null;

        Poster.getInstance().post(new Message(response,listener));
        /**
         * 本质上不是开了个线程，而是直接调用了Message.run()这个方法
         * 直接调用的run()方法还是在原来的线程(也就是looper所在的线程{
         *     因为是在looper中调用handler的dispatchMessage(msg)
         *     在dispatchMessage(msg)->handleCallback(msg){
         *         message.callback.run();//这个例子中就是Message.run()
         *     }
         * })
         *
         */

    }
}
