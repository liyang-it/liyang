package com.liyang.controller.webappVideo;

import com.liyang.json.jsonResult;
import com.liyang.service.video.videoShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/video")
public class videoController {
  @Autowired
  public videoShowService videoShowService;

  @GetMapping(value = "/getShow.json")
  public jsonResult getShow(){
    return new jsonResult(videoShowService.selectShow());
  }
  @GetMapping(value = "/disableShow.json")
  public jsonResult disableShow(){
    return new jsonResult(videoShowService.disableShow());
  }
  // asdasd
  @GetMapping(value = "/enableShow.json")
  public jsonResult enableShow(){
    return new jsonResult(videoShowService.enableShow());
  }
}
