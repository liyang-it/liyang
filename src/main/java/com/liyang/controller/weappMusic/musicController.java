package com.liyang.controller.weappMusic;

import com.liyang.json.jsonResult;
import com.liyang.service.music.controlService;
import com.liyang.tools.redisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/music")
public class musicController {
  @Autowired
  private controlService controlService;
  @Resource
  private redisUtil redisUtil;
  // 查询 是否跳转具体歌单
  @GetMapping(value = "/getIsToGd.json")
  public jsonResult getIsToGd(HttpServletResponse response){
    Integer result = 0;
    if(redisUtil.hasKey("weapp:music:istogd")){
      result = (Integer)redisUtil.get("weapp:music:istogd");
    }else{
      result = controlService.queryIsToGd();
      redisUtil.set("weapp:music:istogd",result);
    }
    return new jsonResult(result);
  }
  // 设置是否跳转具体歌单
  @GetMapping(value = "/setIsToGd.json/{param}")
  public jsonResult setIsToGd(@PathVariable Integer param){
     int result = controlService.setIsToGd(param);
     redisUtil.set("weapp:music:istogd",param);
    return new jsonResult();
  }

}
