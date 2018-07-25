package com.example.dmrken.muhttpupload;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.example.dmrken.muhttpupload.entry.UserInfo;
import com.example.dmrken.muhttpupload.task.HttpListener;
import com.example.dmrken.muhttpupload.task.Request;
import com.example.dmrken.muhttpupload.task.RequestExecutor;
import com.example.dmrken.muhttpupload.task.Response;
import com.example.dmrken.muhttpupload.utils.Constans;
import com.example.dmrken.muhttpupload.utils.Logger;
import com.example.dmrken.muhttpupload.utils.TheadUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_get).setOnClickListener(this);
        findViewById(R.id.btn_head).setOnClickListener(this);
        findViewById(R.id.btn_post).setOnClickListener(this);
        findViewById(R.id.btn_handler_looper).setOnClickListener(this);

    }

    /**
     * 读取服务器响应数据
     *
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
                postRequest();
                break;
            }
            case R.id.btn_handler_looper:{
                handler_loop_test();
                break;
            }
            default:
                break;
        }

    }

    /**
     * 异步处理消息test
     */
    private void handler_loop_test() {
        Request request = new Request("https://github.com/onlyfelony");
        RequestExecutor.INSTANCE.execute(request, new HttpListener() {
            @Override
            public void onSuccess(Response response) {
                //
            }

            @Override
            public void onFail(Exception e) {

            }
        });


    }

    /**
     * 处理get请求
     */
    private void getRequest() {
        TheadUtils.execute(new Runnable() {
            @Override
            public void run() {
                executeGet();
            }
        });
    }

    /**
     * 执行get请求，此方法在子线程执行
     * (android不能在主线程中执行网络请求)
     */
    private void executeGet() {
        //我们暂时使用URLConnection执行网络请求
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        //1.建立连接
        URL url = null;//传入请求地址
        try {
            url = new URL(Constans.URL_UPLOAD);
            // url = new URL("https://www.baidu.com");

            urlConnection = (HttpURLConnection) url.openConnection();
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
            if (responseCode == 200) {//判断服务器响应码为200表示成功
                inputStream = urlConnection.getInputStream();
                readServerData(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();//关闭连接
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 处理HEAD请求
     */
    private void headRequest() {
        TheadUtils.execute(new Runnable() {
            @Override
            public void run() {
                executeHead();
            }
        });
    }

    private void executeHead() {
        //我们暂时使用URLConnection执行网络请求
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        //1.建立连接
        URL url = null;//传入请求地址
        try {
            url = new URL(Constans.URL_UPLOAD);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("HEAD");//HEAD服务器只能发送响应头，不能发送body，客户端也读取不到body

            //2.发送数据
            urlConnection.connect();//连接服务器

            //3.拿到响应
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {//判断服务器响应码为200表示成功
                Map<String, List<String>> stringListMap = urlConnection.getHeaderFields();//得到服务器的所有响应头
                Set<Map.Entry<String, List<String>>> entries = stringListMap.entrySet();// Map<String, List<String>>不能for循环
                for (Map.Entry<String, List<String>> entry : entries) {

                    String headKey = entry.getKey();
                    List<String> headValues = entry.getValue();
                    Logger.i("-------- Head Key: " + headKey + "---------");
                    for (String headValue : headValues) {
                        Logger.i("Head Value: " + headValue);
                    }
                }
/*                inputStream = urlConnection.getInputStream();
                readServerData(inputStream);*///HEAD服务器只能发送响应头，不能发送body，客户端也读取不到body
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();//关闭连接
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * POST、PUT、GET请求
     */
    private void postRequest() {
        TheadUtils.execute(new Runnable() {
            @Override
            public void run() {
                executePost();
            }
        });

    }

    //真正的执行POST请求
    private void executePost() {

        //我们暂时使用URLConnection执行网络请求
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        //1.建立连接
        URL url = null;//传入请求地址
        try {
            url = new URL(Constans.URL_UPLOAD_POST);
            // url = new URL("https://www.baidu.com");

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");//默认是GET
            urlConnection.setRequestProperty("ContentType","application/json");//设置请求头

            //2.发送数据
            urlConnection.connect();//连接服务器

            outputStream = urlConnection.getOutputStream();
            UserInfo userInfo = new UserInfo();
            userInfo.setUserName("dmrken");
            userInfo.setPassWord("x123456");
            userInfo.setSex("man");

            String json = JSON.toJSONString(userInfo);
            outputStream.write(json.getBytes());

/*             String myPostString  = "Test Post Data: dmrken";
             outputStream.write(myPostString.getBytes());*/


            //3.拿到响应
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {//判断服务器响应码为200表示成功
                inputStream = urlConnection.getInputStream();
                readServerData(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();//关闭连接
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
