package com.example.dmrken.muhttpupload;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.dmrken.muhttpupload.utils.Constans;
import com.example.dmrken.muhttpupload.utils.Logger;
import com.example.dmrken.muhttpupload.utils.TheadUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_get).setOnClickListener(this);
        findViewById(R.id.btn_head).setOnClickListener(this);
        findViewById(R.id.btn_post).setOnClickListener(this);

    }

    /**
     * 读取服务器响应数据
     * @param inputStream 服务器的输出流
     * @throws IOException 读取过程中发生的异常
     */
    private void readServerData(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        outputStream.close();
        inputStream.close();
        String data = new String(outputStream.toByteArray());
        Logger.i("服务器响应数据： " + data);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get: {
                getRequest();
                break;
            }
            case R.id.btn_head: {
                headRequest();
                break;
            }
            case R.id.btn_post: {
                posrRequest();
                break;
            }
            default:
                break;
        }

    }

    /**
     * 处理get请求
     */
    private void getRequest() {
        TheadUtils.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    executeGet();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 执行get请求，此方法在子线程执行
     * (android不能在主线程中执行网络请求)
     */
    private void executeGet() throws Exception {
        //我们暂时使用URLConnection执行网络请求

        //1.建立连接
        URL url = new URL(Constans.URL_UPLOAD);//传入请求地址
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
/*        if (urlConnection instanceof HttpsURLConnection) {
            //进行一些设置
            ((HttpsURLConnection) urlConnection).setHostnameVerifier();
            ((HttpsURLConnection) urlConnection).setSSLSocketFactory();
        }*/
        urlConnection.setRequestMethod("GET");//默认是GET

        //2.发送数据
        // urlConnection.getOutputStream();//GET类型的请求不能拿到输出流

        urlConnection.connect();//连接服务器

        //3.拿到响应
        int responseCode = urlConnection.getResponseCode();
        if (responseCode == 200) {//判断响应码
            InputStream inputStream = urlConnection.getInputStream();
            readServerData(inputStream);
        }

    }

    /**
     * 处理head请求
     */
    private void headRequest() {
    }

    private void executeHead() {

    }

    /**
     * 处理posr请求
     */
    private void posrRequest() {
    }

    private void executePost() {

    }
}
