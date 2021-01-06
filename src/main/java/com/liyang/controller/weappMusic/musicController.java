package com.liyang.controller.weappMusic;

import com.liyang.entity.music.pageUrl;
import com.liyang.json.jsonResult;
import com.liyang.service.music.controlService;
import com.liyang.service.music.pageUrlService;
import com.liyang.tools.redisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/music")
public class musicController {
  @Autowired
  private controlService controlService;
  @Autowired
  private pageUrlService pageUrlService;
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
  /**
   * 查询歌单页面URL
   */
  @GetMapping(value = "/getGeDanUrl.json")
  public jsonResult getGeDanUrl(){
    String key = "weapp:music:gedan_url";
    String url = null;
    if(redisUtil.hasKey(key)){
      url = redisUtil.get(key).toString();
    }else{
      url = pageUrlService.queryPageUrl("gedan");
      redisUtil.set(key,url);
    }
    return new jsonResult(url);
  }

  /**
   * 查询搜索页面URL
   */
  @GetMapping(value = "/getSouSuoUrl.json")
  public jsonResult getSouSuoUrl(){
    String key = "weapp:music:sousuo_url";
    String url = null;
    if(redisUtil.hasKey(key)){
      url = redisUtil.get(key).toString();
    }else{
      url = pageUrlService.queryPageUrl("sousuo");
      redisUtil.set(key,url);
    }
    return new jsonResult(url);
  }
  /**
   * 查询所有 pageUrl 数据
   */
  @GetMapping(value = "/getPageUrlAll.json")
  public jsonResult getPageUrlAll(){
    return new jsonResult(pageUrlService.queryAll());
  }
  /**
   * 修改 pageUrl
   */
  @GetMapping(value = "/updatePageUrl.json")
  public jsonResult updatePageUrl(@Validated pageUrl page){
    int result = pageUrlService.updatePageUrl(page);
    return  new jsonResult(result);

  }

}
