package com.liyang.result;

public class resultData {
  private Integer code;
  public String msg;
  public String data;

  @Override
  public String toString() {
    return "resultData{" +
            "code=" + code +
            ", msg='" + msg + '\'' +
            ", data=" + data +
            '}';
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getData() {
    return data;
  }
}
