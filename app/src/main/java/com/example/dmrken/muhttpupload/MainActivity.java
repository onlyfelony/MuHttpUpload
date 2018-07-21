package com.example.dmrken.muhttpupload;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.dmrken.muhttpupload.utils.TheadUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_get).setOnClickListener(this);
        findViewById(R.id.btn_head).setOnClickListener(this);
        findViewById(R.id.btn_post).setOnClickListener(this);

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
                executeGet();
            }
        });
    }

    /**
     * 执行get请求，此方法在子线程执行
     */
    private void executeGet() {

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
