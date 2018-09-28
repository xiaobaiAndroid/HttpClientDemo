package com.bzf.httpclientdemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IOUtils {

    /**
     * 读取流中的信息，转换成字符串
     * @author baizhengfu
     * @return void
     * @throws 
     * @date 2018/9/28
     */
    public static String converToString(InputStream inputStream){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        StringBuffer sb = new StringBuffer();
        String line = null;
        try {
            while((line=bufferedReader.readLine())!=null){
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
