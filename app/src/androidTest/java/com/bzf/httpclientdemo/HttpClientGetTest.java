package com.bzf.httpclientdemo;

import android.util.Log;

import org.junit.Test;

public class HttpClientGetTest {

    @Test
    public void testUseGet() {
        String url = "http://api.apiopen.top/singlePoetry";
        HttpClientUtils httpClientUtils = new HttpClientUtils();
        String content = httpClientUtils.useGet(url);
        Log.i("bzf",content);
    }
}