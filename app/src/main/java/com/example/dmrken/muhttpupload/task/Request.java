package com.example.dmrken.muhttpupload.task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/**
 * Created by dmrken on 2018/7/25.
 * 请求类
 */

public class Request {
    private String url;//请求的url
    private RequestMethod method;//请求的方法

    private List<KeyValue> keyValues;//发送的参数
    private SSLSocketFactory mSslSocketFactory;
    private HostnameVerifier mHostnameVerifier;

    public Request(String url) {
        this(url, RequestMethod.GET);
    }

    public Request(String url, RequestMethod method) {
        this.url = url;
        this.method = method;
        this.keyValues = new ArrayList<>();
    }

    /**
     * 设置SSL证书
     *
     * @param sslSocketFactory {@link SSLSocketFactory}
     */
    public void setSSLSocketFactory(SSLSocketFactory sslSocketFactory) {
        this.mSslSocketFactory = sslSocketFactory;
    }//设置https证书相关信息

    /**
     * 设置服务器主机认证规则
     *
     * @param hostnameVerifier {@link HostnameVerifier}
     */
    public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.mHostnameVerifier = hostnameVerifier;
    }

    public SSLSocketFactory getSslSocketFactory() {
        return mSslSocketFactory;
    }

    public HostnameVerifier getHostnameVerifier() {
        return mHostnameVerifier;
    }

    public String getUrl() {
        return url;
    }

    public RequestMethod getMethod() {
        return method;
    }

    public List<KeyValue> getKeyValues() {
        return keyValues;
    }

    public void add(String key, String value) {
        keyValues.add(new KeyValue(key, value));
    }

    public void add(String key, File value) {
        keyValues.add(new KeyValue(key, value));
    }

    //int型
    public void add(String key, int value) {
        keyValues.add(new KeyValue(key, Integer.toString(value)));
    }

    //long型
    public void add(String key, long value) {
        keyValues.add(new KeyValue(key, Long.toString(value)));
    }

    @Override
    public String toString() {
        return "url:" + url + "'method:" + method + ";params:" + keyValues.toString();
    }
}
