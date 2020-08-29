package com.liyang.task;

import com.alibaba.fastjson.JSON;
import com.liyang.entity.Playlist;
import com.liyang.entity.song;
import com.liyang.service.playListService;
import com.liyang.service.playListTimeService;
import com.liyang.tools.dateTimeTool;
import com.liyang.tools.httpRequestTools;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Configuration
@Component
@EnableScheduling
/**
 * 定时任务
 * 定时更新 每日推荐歌单，以及歌单包含的歌曲
 */
public class TaskUpdateMusic {
  @Resource
  public playListService service;
  @Autowired
  public playListTimeService service2;
  public Logger log = LoggerFactory.getLogger(TaskUpdateMusic.class);

  /**
   * 每天 4点 获取网易云每日推荐歌单列表  插入到数据库中
   */
  @Scheduled(cron = "0 22 12 * * ? ")
  public void setPlayList(){
    // 发送http请求获取歌单列表
    log.info("日期[{}];开始登陆网易云，并且 发送请求 获取每日推荐歌单列表",dateTimeTool.getYmd());
    // 每日推荐歌单需要登录 之后获取
    String loginUrl = "https://musicapi.leanapp.cn/login/cellphone?phone=15575141967&password=liyang664243";
    String loginStr = httpRequestTools.get(loginUrl);
    JSONObject loginJson = JSONObject.fromObject(loginStr);
    log.info("登录状态[{}]",loginJson.get("code"));
    String gdListUrl = "https://musicapi.leanapp.cn/personalized?limit=250";
    String gdStr = httpRequestTools.get(gdListUrl);
    JSONObject gdJson = JSONObject.fromObject(gdStr);
    // 获取到歌单json列表后转换成具体 集合对象
    List<Playlist> gdList = JSON.parseArray(gdJson.get("result").toString(),Playlist.class);
    // 将获取到的歌单信息 插入到自己数据库中
    String ymdStr = dateTimeTool.getYmd();
    service2.addPlayListTime(ymdStr);
    for (int i = 0;i <gdList.size();i++){
      Playlist p = gdList.get(i);
      p.setPlaylisttimeid(ymdStr);
      service.addPlayList(p);
    }
    log.info("日期 :[{}]; 每日推荐歌单列表更新成功",dateTimeTool.getYmd());
  }

  /**
   * 根据歌曲id 返回歌曲详细
   * @param id 歌曲id
   * @return
   */
  public song getSong(String id){
    log.info("获取歌曲 ID:[{}] 的信息",id);
    String getSongUrl = "https://musicapi.leanapp.cn/song/detail?ids="+id;
    String songStr = httpRequestTools.get(getSongUrl);
    //得到歌曲json字符串
    String json = JSONObject.fromObject(songStr).get("songs").toString();
    // 因为返回的歌曲json字符串是数组对象，但是只有一条数据 ，需要把数组的[]括号去去掉然后转换具体歌曲 JSONObject对象
    json = json.substring(json.indexOf("[")+1,json.lastIndexOf("]"));
    // 把处理好的歌曲json字符串转换为 JSONObject对象
    JSONObject songObject = JSONObject.fromObject(json);
    // 创建歌曲实体类赋值
    song s = new song();
    s.setId(songObject.get("id").toString());
    s.setName(songObject.get("name").toString());
    s.setMvId(songObject.get("mv").toString());
    //得到歌曲 专辑json字符串
    String alJson = songObject.get("al").toString();
    JSONObject alObject = JSONObject.fromObject(alJson);
    s.setAlId(alObject.get("id").toString());
    s.setAlName(alObject.get("name").toString());
    s.setAlPicUrl(alObject.get("picUrl").toString());
    s.setAlPic(alObject.get("pic").toString());
    //得到歌曲 作者信息json字符串
    String arJson = songObject.get("ar").toString();
    arJson = arJson.substring(arJson.indexOf("[")+1,arJson.lastIndexOf("]"));
    JSONObject arObject = JSONObject.fromObject(arJson);
    s.setArId(arObject.get("id").toString());
    s.setArName(arObject.get("name").toString());
    return s;
  }
}
