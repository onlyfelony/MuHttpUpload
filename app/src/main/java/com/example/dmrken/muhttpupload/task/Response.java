package com.example.dmrken.muhttpupload.task;

/**
 * Created by dmrken on 2018/7/25.
 */

public class Response {
    /**
     * 服务器的响应码
     */
    private int responseCode;

    /**
     * 服务器响应数据
     */
    private String result;

    /**
     * 请求过程中发生的错误
     */
    private Exception exception;

    public Response(int responseCode, String result, Exception exception) {
        this.responseCode = responseCode;
        this.result = result;
        this.exception = exception;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
