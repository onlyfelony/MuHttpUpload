package com.example.dmrken.muhttpupload.task;

import com.example.dmrken.muhttpupload.urlconnection.URLConnectionFactory;
import com.example.dmrken.muhttpupload.utils.Logger;
import com.example.dmrken.muhttpupload.utils.Poster;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/**
 * Created by dmrken on 2018/7/25.
 * 执行请求的线程
 */

public class RequestTask implements Runnable {
    private Request request;
    private HttpListener listener;

    public RequestTask(Request request, HttpListener listener) {
        this.request = request;
        this.listener = listener;
    }


    @Override
    public void run() {
        //执行请求
        Logger.i("执行请求开始：" + request.toString());

        Exception exception = null;
        int responseCode = -1;
        Map<String, List<String>> responseHeaders = null;

        String urlStr = request.getUrl();
        RequestMethod method = request.getMethod();
        Logger.i("url:" + urlStr + "method:" + method);

        HttpURLConnection urlConnection = null;
        try {
            //1.建立连接
            URL url = new URL(urlStr);


            //  urlConnection = (HttpURLConnection) url.openConnection();
            //切换okHttp和URLConnection
            urlConnection = URLConnectionFactory.getInstance().openUrl(url);


            //https的处理
            if (urlConnection instanceof HttpsURLConnection) {
                //如果url是以https开头
                HttpsURLConnection httpsURLConnection = ((HttpsURLConnection) urlConnection);
                SSLSocketFactory sslSocketFactory = request.getSslSocketFactory();
                if (sslSocketFactory != null)
                    httpsURLConnection.setSSLSocketFactory(sslSocketFactory);//https证书相关信息
                HostnameVerifier hostnameVerifier = request.getHostnameVerifier();
                if (hostnameVerifier != null)
                    httpsURLConnection.setHostnameVerifier(hostnameVerifier);//服务器主机认证
            }

            urlConnection.setRequestMethod(method.getValue());

            //2.发送数据  TODO..

            //3.读取响应  TODO..
        } catch (IOException e) {
            exception = e;
        } finally {
            urlConnection.disconnect();
        }


        // TODO 拿到响应
        Response response = new Response(request, responseCode, responseHeaders, "This is test responseData", null);

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
        Poster.getInstance().post(new Message(response, listener));


    }
}
