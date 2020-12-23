package com.liyang.json;

public class jsonResult<T> {
  private static Integer code;
  private static String msg;
  private  T data;

  public jsonResult() {
  }

  public jsonResult(T data) {
    this.code = 200;
    this.msg = "操作成功";
    this.data = data;
  }

  public jsonResult(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }
  public static jsonResult resultError(String message){
    jsonResult r = new jsonResult();
    r.setCode(500);
    r.setMsg(message);
    return r;
  }
  public void setCode(Integer code) {
    this.code = code;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public void setData(T data) {
    this.data = data;
  }

  public Integer getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }

  public T getData() {
    return data;
  }
}
