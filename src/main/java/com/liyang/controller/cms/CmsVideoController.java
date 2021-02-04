package com.liyang.controller.cms;

import com.liyang.json.jsonResult;
import com.liyang.service.cms.videoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/lyVideo")
public class CmsVideoController {
  @Autowired
  private videoService videoService;

  @GetMapping(value = "/getVideoList.json")
  public jsonResult getVideo(String name,int page,int limit){
    String name_ = "%"+name+"%";
    return new jsonResult(videoService.queryVideoByNameAndPage(name_,page,limit));
  };
  @GetMapping(value = "/getVideoDetailed.json")
  public jsonResult getVideoDetailed(int id){
    return new jsonResult(videoService.queryVideoById(id));
  }
}
