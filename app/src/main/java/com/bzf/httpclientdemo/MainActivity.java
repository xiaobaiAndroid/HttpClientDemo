package com.bzf.httpclientdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MainView{


    private MainPresenter presenter;
    private TextView contentTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contentTV = findViewById(R.id.contentTV);

        findViewById(R.id.getBt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentTV.setText("");
                presenter.testGet("http://api.apiopen.top/recommendPoetry");
            }
        });

        findViewById(R.id.postBt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentTV.setText("");
                presenter.testPost("http://api.apiopen.top/searchPoetry","相思");
            }
        });

        presenter = new MainPresenter(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destory();
    }

    @Override
    public void requestSuccess(String content) {
        contentTV.setText(content);
    }

    @Override
    public void requestFail() {
        contentTV.setText("请求失败");
    }
}
