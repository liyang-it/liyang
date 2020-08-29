package com.liyang.tools;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class httpRequestTools {
  public static String get(String toUrl) {
    StringBuffer sb = new StringBuffer();
    try {
      //创建 URL 对象
      URL url = new URL(toUrl);
      //通过URL对象打开一个http连接
      HttpURLConnection http = (HttpURLConnection)url.openConnection();
      //设置请求方法
      http.setRequestMethod("GET");
      //获取请求 字节输出流
      InputStream inputStream = http.getInputStream();
      //把字节输出流转换成 字符输出流
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
      //使用字符缓冲流
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
      //创建stringBuffer
      String outStr = null;
      // 输出
      while ((outStr = bufferedReader.readLine()) != null){
        sb.append(outStr);
      }
      //释放资源
      bufferedReader.close();
      inputStreamReader.close();
      inputStream.close();
      //System.out.println("请求返回值：" + sb.toString());
    } catch (MalformedURLException e) {
      System.out.println("创建URL对象失败！原因" + e.getMessage());
    } catch (IOException io){
      System.out.println("打开http连接对象失败！原因" + io.getMessage());
    }
    return sb.toString();
  }
}
