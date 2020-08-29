package com.liyang;

import com.alibaba.fastjson.JSON;
import com.liyang.entity.Track;
import com.liyang.service.playListService;
import com.liyang.service.playListTimeService;
import com.liyang.service.songService;
import com.liyang.service.trackService;
import com.liyang.task.TaskUpdateMusic;
import com.liyang.tools.httpRequestTools;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LiyangApplicationTests {
  @Autowired
  public playListService service;
  @Autowired
  public playListTimeService service2;
  @Autowired
  public trackService service3;
  @Autowired
  public songService service4;

  @Test
  public void contextLoads() {
    //根据歌单Id 查询详细的歌单信息
    String toUrl = "https://musicapi.leanapp.cn/playlist/detail?id=2409342460";
    String resultJson = httpRequestTools.get(toUrl);
    //得到歌单详细信息json字符串
    JSONObject jsonObject = JSONObject.fromObject(resultJson);
    String gdJson = jsonObject.get("playlist").toString();
    //获取歌单内的歌曲ID集合
    JSONObject trackObject = JSONObject.fromObject(gdJson);
    List<Track> trackIds = (List<Track>) JSON.parseArray(trackObject.get("trackIds").toString(),Track.class);
    System.out.println("歌曲数量：" +trackIds.size());
    // 新增歌曲到数据库
    for (int i = 0; i<trackIds.size();i++){
      System.out.println(trackIds.get(i).getId());
      Track t = trackIds.get(i);
      t.setGdId("2409342460");
      service3.addTrack(t);
      //添加完 歌单与歌曲连接信息后 添加歌曲
      TaskUpdateMusic task = new TaskUpdateMusic();
      service4.addSong(task.getSong(t.getId()));

    }

  }

}
