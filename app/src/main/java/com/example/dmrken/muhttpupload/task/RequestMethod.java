package com.example.dmrken.muhttpupload.task;

/**
 * Created by dmrken on 2018/7/25.
 */

public enum RequestMethod {

    GET("GET"),
    POST("POST"),
    HEAD("HEAD"),
    DELETE("DELETE");

    private String value;

    RequestMethod(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
