package com.example.dmrken.muhttpupload.task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmrken on 2018/7/25.
 */

/**
 * 请求类
 */
public class Request {
    private String url;//请求的url
    private RequestMethod method;//请求的方法

    private List<KeyValue> keyValues;//发送的参数

    public Request(String url) {
        this(url,RequestMethod.GET);
    }

    public Request(String url, RequestMethod method) {
        this.url = url;
        this.method = method;
        this.keyValues = new ArrayList<>();
    }

    public void add(String key,String value){
        keyValues.add(new KeyValue(key,value));
    }

    public void add(String key, File value){
        keyValues.add(new KeyValue(key,value));
    }

    public void add(String key,int value){
        keyValues.add(new KeyValue(key,Integer.toString(value)));
    }

    public void add(String key,long value){
        keyValues.add(new KeyValue(key,Long.toString(value)));
    }
}
