package com.liyang.tools;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class saveUrlFIle {
  public static String path = "/liyang/temp_video/";
  public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
  public static double getUrlFileSize(String toUrl) {
    double result = 0;
    HttpURLConnection conn = null;
    try {
      URL url = new URL(toUrl);

      conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("HEAD");
      conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows 7; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.73 Safari/537.36 YNoteCef/5.8.0.1 (Windows)");
      result = ((double) conn.getContentLength()) / 1000000;
    } catch (Exception e) {
    } finally {
      conn.disconnect();
    }
    return result;
  }
  public static String saveVideo(String toUrl){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    try {
      File file =new File(path);

      if(!file.exists()){
        file.mkdir();
      }
      String fileName = sdf.format(new Date()) + ".mp4";
      URL url = new URL(toUrl);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
      FileOutputStream fos = new FileOutputStream(path + fileName);
      byte[] buffer = new byte[4096];
      int count = 0;
      while ((count = bis.read(buffer)) > 0) {
        fos.write(buffer, 0, count);
      }
      fos.close();
      bis.close();
      return fileName;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
