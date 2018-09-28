package com.bzf.httpclientdemo;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 *  HttpClient get请求示例
 * @author baizhengfu
 * @version 1.0
 * @since JDK1.8
 * @date 2018/9/28
 */
public class HttpClientUtils {

    private  int timeOut = 15000;

    private static final int STATUS_SUCCESS = 200;//请求成功

    /**
     * 创建HttpClient
     * @author baizhengfu
     * @return void
     * @throws 
     * @date 2018/9/28
     */
    private  HttpClient creatHttpClient(){
        HttpParams httpParams = new BasicHttpParams();

        HttpConnectionParams.setConnectionTimeout(httpParams,timeOut);
        HttpConnectionParams.setSoTimeout(httpParams,timeOut);
        HttpConnectionParams.setTcpNoDelay(httpParams,true);
        //设置Http协议版本
        HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
        //设置内容编码
        HttpProtocolParams.setContentCharset(httpParams, HTTP.UTF_8);
        //持续握手
        HttpProtocolParams.setUseExpectContinue(httpParams,true);
        DefaultHttpClient httpClient = new DefaultHttpClient(httpParams);
        return httpClient;
    }


    public String useGet(String url){
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader(HTTP.CONN_DIRECTIVE,HTTP.CONN_KEEP_ALIVE);

        HttpClient httpClient = creatHttpClient();
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            int statusCode = httpResponse.getStatusLine().getStatusCode();

            if(statusCode==STATUS_SUCCESS){
                InputStream inputStream = entity.getContent();
                String content = IOUtils.converToString(inputStream);

                inputStream.close();
                return  content;
            }else{

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


    public String usePost(String url,String name){
        HttpPost httpPost = new HttpPost(url);

        httpPost.addHeader(HTTP.CONN_DIRECTIVE,HTTP.CONN_KEEP_ALIVE);
        HttpClient httpClient = creatHttpClient();

        List<NameValuePair> postParams = new ArrayList<>();

        //要传递的参数
        postParams.add(new BasicNameValuePair("name",name));

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(postParams));
            try {
                HttpResponse httpResponse = httpClient.execute(httpPost);
                int statusCode = httpResponse.getStatusLine().getStatusCode();

                Log.i("bzf","statusCode="+statusCode);
                if(statusCode==STATUS_SUCCESS){
                    HttpEntity httpEntity = httpResponse.getEntity();
                    InputStream inputStream = httpEntity.getContent();

                    String content = IOUtils.converToString(inputStream);

                    inputStream.close();
                    return content;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
