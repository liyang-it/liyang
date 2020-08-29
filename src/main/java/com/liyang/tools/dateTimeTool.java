package com.liyang.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

public class dateTimeTool {
  public static SimpleDateFormat ymd = new SimpleDateFormat("yyyyMMdd");
  public static SimpleDateFormat ymdhs = new SimpleDateFormat("yyyy-MM-dd HH:ss");

  /**
   * 获取 yyyyMMdd格式日期字符串
   * @return
   */
  public static String getYmd(){
    Date date = new Date();
    String str = ymd.format(date);
    return str;
  }

  /**
   * 获取 yyyy-MM-dd HH:ss 日期格式字符串
   * @return
   */
  public static String getYmdhs(){
    Date date = new Date();
    String str = ymdhs.format(date);
    return str;
  }



}
