package com.example.dmrken.muhttpupload.task;

import java.io.File;

/**
 * Created by dmrken on 2018/7/25.
 * 发送的参数
 */

public class KeyValue {

    private String key;
    private Object value;//在构造函数中限制类型为String、File

    public KeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public KeyValue(String key, File value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }
}
