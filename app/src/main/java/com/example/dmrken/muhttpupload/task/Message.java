package com.example.dmrken.muhttpupload.task;

/**
 * Created by dmrken on 2018/7/25.
 */

public class Message implements Runnable {

    private Response response;
    private HttpListener listener;

    public Message(Response response, HttpListener listener) {
        this.response = response;
        this.listener = listener;
    }

    @Override
    public void run() {
        //通过listener回调到主线程

/*        //把response或者exception通过listener传给主线程
        listener.onSuccess(response);//成功
        listener.onFail(response.getException());//失败*/
    }
}
