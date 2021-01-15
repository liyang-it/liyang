package com.liyang;

import com.alibaba.fastjson.JSON;
import com.liyang.entity.Playlist;
import com.liyang.entity.Track;
import com.liyang.entity.music.rmpl;
import com.liyang.service.*;
import com.liyang.service.music.rmplService;
import com.liyang.task.TaskUpdateMusic;
import com.liyang.tools.httpRequestTools;
import com.liyang.tools.redisUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LiyangApplicationTests {
  public Logger log = LoggerFactory.getLogger(LiyangApplicationTests.class);
  @Autowired
  public playListService service;
  @Autowired
  public playListTimeService service2;
  @Autowired
  public trackService service3;
  @Autowired
  public songService service4;
  @Autowired
  public TaskUpdateMusic taskUpdateMusic;
  @Resource
  public redisUtil redisUtil;
  @Autowired
  public com.liyang.service.systemAsNumberService systemAsNumberService;

  @Autowired
  public rmplService rmplService;
  @Test
  public void contextLoads() {
  // 云村热门评论 url
//    String ycUrl = "http://liyangit.top:3000/comment/hotwall/list";
//    try {
//      String str = httpRequestTools.get(ycUrl);
//      JSONObject json = JSONObject.fromObject(str);
//      JSONArray array = JSONArray.fromObject(json.get("data"));
//      System.out.println();
//      for (Object a: array) {
//        JSONObject aob = JSONObject.fromObject(a);
//        System.out.println("内容:" + aob.get("content"));
//        JSONObject user = JSONObject.fromObject(aob.get("simpleUserInfo"));
//        System.out.println("用户名："+ user.get("nickname"));
//        System.out.println("头像："+ user.get("avatar"));
//      }
//      System.out.println(array.size());
//
//    }catch (Exception e){
//      e.printStackTrace();
//    }
    // 先获取热门 30 个热门歌手
    String rmGs = "http://liyangit.top:3000/top/artists?offset=0&limit=30";
    try {
      String rmgsStr = httpRequestTools.get(rmGs);
      JSONArray rmgsArray = JSONArray.fromObject(JSONObject.fromObject(rmgsStr).get("artists"));
      for (Object gs: rmgsArray) {
        String gsId = JSONObject.fromObject(gs).get("id").toString();
        // 拿到歌手id 获取 获取歌手热门50首音乐
        String rmGq = "http://liyangit.top:3000/artist/top/song?id="+gsId;
        String rmgqStr = httpRequestTools.get(rmGq);
        JSONArray rmgqArray = JSONArray.fromObject(JSONObject.fromObject(rmgqStr).get("songs"));
        for (Object rmgq:rmgqArray) {
          String gqId = JSONObject.fromObject(rmgq).get("id").toString();
          // 拿到歌曲名
          String song = JSONObject.fromObject(rmgq).get("name").toString();
          // 拿到歌曲ID  获取 50个热门 评论保存到数据库
          String rmPl = "http://liyangit.top:3000/comment/hot?id="+gqId+"&type=0&limit=50";
          String rmplStr = httpRequestTools.get(rmPl);
          JSONArray rmplArray  = JSONArray.fromObject(JSONObject.fromObject(rmplStr).get("hotComments"));
          for (Object rmplS:rmplArray) {
            // 评论内容
            String content = JSONObject.fromObject(rmplS).get("content").toString();
            // 评论用户名
            JSONObject user = JSONObject.fromObject(JSONObject.fromObject(rmplS).get("user")) ;
            String username = user.get("nickname").toString();
            // 评论用户头像
            String avatar = user.get("avatarUrl").toString();
            System.out.println("评论内容:" + content);
            // 将评论数据保存进数据库
            rmpl  rmpl = new rmpl(content,username,avatar,song);
            try {
              rmplService.insertRmpl(rmpl);
            }catch (Exception e){}

          }
        }
      }

    }catch(Exception e){
      e.printStackTrace();
    }
  }

}
