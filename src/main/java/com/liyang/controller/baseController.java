package com.liyang.controller;

import com.liyang.result.resultData;

public class baseController {
  public resultData resultData = new resultData();
  public resultData errorResult(int code,String msg){
    resultData.setCode(code);
    resultData.setMsg(msg);
    return resultData;
  }

  public resultData successResult(int code,String msg,String data){
    resultData.setCode(code);
    resultData.setMsg(msg);
    resultData.setData(data);
    return resultData;
  }
}
