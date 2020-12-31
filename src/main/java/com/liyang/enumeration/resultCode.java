package com.liyang.enumeration;

public enum resultCode {
  RESULT_SUCCESS(200,"操作成功");

  private int code;
  private String msg;

  resultCode(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
