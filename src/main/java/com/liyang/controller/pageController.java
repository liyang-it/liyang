package com.liyang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 页面控制器
 */
@Controller
public class pageController {

  @RequestMapping(value = "/testJson.html",method = RequestMethod.GET)
  @ResponseBody
  public String testJson(String name){
    String str = "你好呀   "+name;
    return str;
  }
  @RequestMapping(value ="/")
  public String index(){
    return "index";
  }
  @RequestMapping(value ="/index")
  public String index2(){
    return "index2";
  }
  @RequestMapping(value ="/head")
  public String head(){
    return "head";
  }
  @RequestMapping(value ="/foot")
  public String foot(){
    return "foot";
  }
}
