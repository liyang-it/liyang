package com.liyang.controller.webappVideo;

import com.liyang.json.jsonResult;
import com.liyang.service.video.videoShowService;
import com.liyang.tools.saveUrlFIle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/video")
public class videoController {
  @Autowired
  public videoShowService videoShowService;

  @GetMapping(value = "/getShow.json")
  public jsonResult getShow() {
    return new jsonResult(videoShowService.selectShow());
  }

  @GetMapping(value = "/disableShow.json")
  public jsonResult disableShow() {
    return new jsonResult(videoShowService.disableShow());
  }

  // asdasd
  @GetMapping(value = "/enableShow.json")
  public jsonResult enableShow() {
    return new jsonResult(videoShowService.enableShow());
  }

  // 通过视频链接临时保存
  @PostMapping(value = "/saveVideo.json")
  public jsonResult saveVideo(@RequestParam("url") String url, HttpServletRequest request) {
    double size = saveUrlFIle.getUrlFileSize(url);
    if (size >= 45) {
      return new jsonResult(505, "文件超出限制，不允许保存");
    } else {
      String fileName = saveUrlFIle.saveVideo(url);
      String videoUrl = request.getRequestURL().toString();
      videoUrl = videoUrl.substring(0, videoUrl.lastIndexOf("/"));
      videoUrl = videoUrl.substring(0, videoUrl.lastIndexOf("/"));
      videoUrl = videoUrl + "/file/getTempVideo/" + fileName;
      return new jsonResult(200,videoUrl);
    }
  }
}
