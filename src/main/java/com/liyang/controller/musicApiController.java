package com.liyang.controller;
import com.liyang.entity.Playlist;
import com.liyang.entity.song;
import com.liyang.json.jsonResult;
import com.liyang.pojo.music;
import com.liyang.service.*;
import com.liyang.tools.httpRequestTools;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 音乐api
 */
@RestController
@RequestMapping("/music")
@CrossOrigin
public class musicApiController extends baseController{
  @Autowired
  private playListService playListService;
  @Autowired
  private playListTimeService playListTimeService;
  @Autowired
  private songService songService;
  @Autowired
  private trackService trackService;

  /**
   * 根据 每日推荐歌单列表
   * @return jsonResult
   */
  @RequestMapping(value = "/getPlayList.json")
  public jsonResult getPlayList(Playlist p) {
    List<Playlist> list = playListService.queryPlayListPageAll(p);
    return new jsonResult(list);
  }

  /**
   *
   * @param gdid 歌单ID
   * @return jsonResult
   */
  @RequestMapping( value = "/getSongs.json")
  public jsonResult getSongs(song s){
    List<song> list = songService.querySongsPageAll(s);
    return new jsonResult(list);
  }

  /**
   *
   * @param gdid 歌单ID
   * @return jsonResult
   */
  @RequestMapping( value = "/getMusicAllByGdid.json")
  public jsonResult MusicAllByGdid(song s){
    List<music> list = songService.queryMusicAllByGdid(s);
    for(int i = 0;i<list.size();i++){
      //根据Id 查询音乐URL  音乐音乐url是有时效性的  必须 实时获取；
      String getMusicUrl = "https://musicapi.leanapp.cn/music/url?id="+list.get(i).getId();
      String jsonStr = httpRequestTools.get(getMusicUrl);
      JSONObject jsonObject = JSONObject.fromObject(jsonStr);
      String dataStr = jsonObject.get("data").toString();
      //得到音乐url对象
      JSONObject dataObject = JSONObject.fromObject(dataStr.substring(1,dataStr.length() - 1));
      //返回音乐URl
      String url = dataObject.get("url").toString();
      list.get(i).setSrc(url);
    }
    return new jsonResult(list);
  }
  /**
   *
   * @param gdid 歌单ID
   * @return jsonResult 不包含音乐url
   */
  @RequestMapping( value = "/getMusicAllByGdidNotUrl.json")
  public jsonResult MusicAllByGdidNotUrl(song s){
    List<music> list = songService.queryMusicAllByGdid(s);
    return new jsonResult(list);
  }

  }

