package com.example.dmrken.muhttpupload.task;

/**
 * Created by dmrken on 2018/7/25.
 */

public interface HttpListener {

    void onSuccess(Response response);

    void onFail(Exception e);

}
