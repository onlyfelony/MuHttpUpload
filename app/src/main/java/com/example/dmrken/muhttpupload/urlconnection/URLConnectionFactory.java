package com.example.dmrken.muhttpupload.urlconnection;

import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.internal.huc.OkHttpURLConnection;
import okhttp3.internal.huc.OkHttpsURLConnection;

/**
 * <p>把okHttp转URLConnection</p>
 * Created by dmrken on 2018/7/26.
 */

public class URLConnectionFactory {
    private static URLConnectionFactory instance;//单例模式

    public static URLConnectionFactory getInstance() {
        if (instance == null)
            synchronized (URLConnectionFactory.class) {
                if (instance == null)
                    instance = new URLConnectionFactory();
            }
        return instance;
    }

    private OkHttpClient okHttpClient;

    private URLConnectionFactory() {

        okHttpClient = new OkHttpClient();//保证okHttpClient也是单例的
    }

    /**
     * 打开url
     *
     * @param url {@link URL}
     * @return
     */
    public HttpURLConnection openUrl(URL url) {
        return openUrl(url, null);
    }

    /**
     * 打开url
     *
     * @param url   {@link URL}
     * @param proxy http代理设置 {@link Proxy}
     * @return
     */
    public HttpURLConnection openUrl(URL url, Proxy proxy) {
            String protocol = url.getProtocol();//http or https协议
            OkHttpClient copy = okHttpClient.newBuilder()
                    .proxy(proxy)
                    .build();//构造OkHttpClient对象

            if (protocol.equals("http")) return new OkHttpURLConnection(url, copy);
            if (protocol.equals("https")) return new OkHttpsURLConnection(url, copy);
            throw new IllegalArgumentException("Unexpected protocol: " + protocol);
    }

}
