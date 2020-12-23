package com.liyang.controller;

import com.liyang.result.resultData;
import com.liyang.tools.httpRequestTools;
import com.liyang.tools.redisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
@CrossOrigin
@RestController
@RequestMapping(value = "/")
public class liyangitController extends  baseController{
  private Logger log = LoggerFactory.getLogger(liyangitController.class);
  @Resource
  private redisUtil redisUtil;
  // 得到访问人数
  @GetMapping("/getAccessNumber.json")
  public resultData getAccessNumber(){
    String accessNumber = String.valueOf(redisUtil.get("liyangit:accessNumber"));
    return successResult(200,"获取成功",accessNumber);
  }
  // 访问人数递增
  @GetMapping("/incrAccessNumber.json")
  public resultData incrAccessNumber(HttpServletRequest request){
    // 一个ip地址一分钟内统计一次
    String ip = httpRequestTools.getIpAddr(request);
    StringBuilder sb = new StringBuilder("liyangit");
    sb.append(ip);
    sb.append("_IP");

    if(!redisUtil.hasKey(sb.toString())){
      redisUtil.incr("liyangit:accessNumber",1);
      redisUtil.set(sb.toString(),ip,30);
    }
    return successResult(200,"操作成功",null);
  }
}
