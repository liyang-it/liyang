package com.liyang.task;

import com.alibaba.fastjson.JSON;
import com.liyang.entity.Playlist;
import com.liyang.entity.Track;
import com.liyang.entity.song;
import com.liyang.service.playListService;
import com.liyang.service.playListTimeService;
import com.liyang.service.songService;
import com.liyang.service.trackService;
import com.liyang.tools.dateTimeTool;
import com.liyang.tools.httpRequestTools;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

@Configuration
@Component
@EnableScheduling
/**
 * 定时任务
 * 定时更新 每日推荐歌单，以及歌单包含的歌曲
 */
public class TaskUpdateMusic {
  @Resource
  public playListService playListService;
  @Autowired
  public playListTimeService playListTimeService;
  @Autowired
  public com.liyang.service.trackService trackService;
  @Autowired
  public songService songService;

  public Logger log = LoggerFactory.getLogger(TaskUpdateMusic.class);
  /**
   * 每天 1点刷新
   */
  @Scheduled(cron = "0 0 1 * * ? ")
  public void delPlayList1(){
    this.runDelMusic();
    this.runAddMusic();
  }

  /**
   * 每天 10点刷新
   */
  @Scheduled(cron = "0 0 10 * * ? ")
  public void delPlayList2(){
    this.runDelMusic();
    this.runAddMusic();
  }

  /**
   * 每天 16点刷新
   */
  @Scheduled(cron = "0 0 16 * * ? ")
  public void delPlayList3(){
    this.runDelMusic();
    this.runAddMusic();
  }


  /**
   * 删除音乐任务
   */
  public void runDelMusic(){
    long startTime = System.currentTimeMillis();
    log.info("开始 删除所有歌曲信息");
    int si = songService.deleteSong();
    log.info("删除歌曲音乐状态 [{}] ",si);
    int ti = trackService.deleteTrack();
    log.info("删除中间信息状态 [{}] ",ti);
    int pi = playListService.deletePlayList();
    log.info("删除歌单状态 [{}] ",pi);
    int pid = playListTimeService.deletePlayListTime();
    log.info("删除歌曲歌单创建时间状态 [{}] ",pid);
    long stopTime = System.currentTimeMillis();
    log.info("日期 :[{}]; 每日推荐歌单列表更新成功;一共耗时 [{}] 秒",dateTimeTool.getYmd(),(stopTime - startTime)/1000);
  }

  /**
   * 新增音乐任务
   */
  public void runAddMusic(){
    log.info("开始 跟新每日歌单信息");
    long startTime = System.currentTimeMillis();
    //默认登录信息
    String phone = "15575141967";
    String password = "liyang664243";
    // 将获取到的歌单信息 插入到自己数据库中

    String ymdStr = dateTimeTool.getYmd();//创建当前日期唯一id
    List<Playlist> list = getPlayList(phone,password,162); //获取歌单 列表
    for (int i = 0;i <list.size();i++){
      //新增歌单详细信息
      Playlist p = list.get(i);
      p.setPlaylisttimeid(ymdStr);
      playListService.addPlayList(p);
      //获取歌单内所有音乐并且新增到数据库
      addSong(p.getId(),ymdStr);
    }
    playListTimeService.addPlayListTime(ymdStr);//新增 当天日期信息
    long stopTime = System.currentTimeMillis();
    log.info("日期 :[{}]; 每日推荐歌单列表更新成功;一共耗时 [{}] 秒",dateTimeTool.getYmd(),(stopTime - startTime)/1000);
  }
  /**
   *  每日登录网易云获取  每日推荐歌单列表
   * @param phone 手机号
   * @param password  密码
   * @param limit  需要获取歌单条数
   * @return
   */
  public List<Playlist> getPlayList(String phone,String password,int limit){
    // 发送http请求获取歌单列表
    log.info("日期[{}];开始登陆网易云，并且 发送请求 获取每日推荐歌单列表",dateTimeTool.getYmd());
    boolean isTrue = false;
    List<Playlist> gdList = new ArrayList<Playlist>();
    int executeCount = 0;
    while (isTrue = true){
      executeCount++;
      // 每日推荐歌单需要登录 之后获取
      String loginUrl = "http://47.99.165.122:3000/login/cellphone?phone=PHONE&password=PASSWORD";
      loginUrl = loginUrl.replace("PHONE",phone).replace("PASSWORD",password);
      // 发送登录请求
      String loginStr = httpRequestTools.get(loginUrl);
      JSONObject loginJson = JSONObject.fromObject(loginStr);
      log.info("登录状态[{}]",loginJson.get("code"));
      // 准备发送请求获取 每日推荐歌单
      String gdListUrl = "http://47.99.165.122:3000/personalized?limit="+ limit;
      String gdStr = httpRequestTools.get(gdListUrl);
      JSONObject gdJson = JSONObject.fromObject(gdStr);
      String code = gdJson.get("code").toString();
      log.info("请求获取歌单 状态 [{}] ",code);
      if(code.equals("-601")){
        limit = limit - 20;
        log.error("请求获取歌单列表信息失败,原因[{}],减少查询歌单数据量，重新发送请求,歌单获取条数减少为:[{}]",gdJson.get("batchMsg").toString(),limit);
      }else{
        // 获取到歌单json列表后转换成具体 集合对象
        gdList = JSON.parseArray(gdJson.get("result").toString(),Playlist.class);
        log.info("获取每日推荐歌单成功,一共获取[{}] 条歌单信息",gdList.size());
        isTrue = true;
        break;
      }
    }
    log.info("执行获取每日推荐歌单 共 [{}] 次",executeCount );
    return gdList;


  }
  /**
   * 根据歌单ID 查询歌单下的所有歌曲新增到数据库
   * @param gdid
   */
  public void addSong(String  gdid,String ymdStr){
    //根据歌单Id 查询详细的歌单信息
    log.info("准备获取歌单内所有歌曲信息 ;歌单ID[{}]",gdid);
    String toUrl = "http://47.99.165.122:3000/playlist/detail?id="+gdid;
    String resultJson = httpRequestTools.get(toUrl);
    //得到歌单详细信息json字符串
    JSONObject jsonObject = JSONObject.fromObject(resultJson);
    String gdJson = jsonObject.get("playlist").toString();
    //获取歌单内的歌曲ID集合
    JSONObject trackObject = JSONObject.fromObject(gdJson);
    List<Track> trackIds = (List<Track>) JSON.parseArray(trackObject.get("trackIds").toString(),Track.class);
    // 新增歌曲到数据库
    for (int i = 0; i<trackIds.size();i++){
      // 新增 歌单与歌曲之间桥梁的中间信息 ---
      Track t = trackIds.get(i);
      t.setGdId(gdid);
      //添加完 (歌单与歌曲连接信息)后 添加歌曲信息
      try {
        songService.addSong(getSong(t.getId(),ymdStr));
        trackService.addTrack(t);
      }catch(Exception e){
        log.error("歌曲ID[{}]新增数据库失败，原因",e.getMessage());
      }

    }
    log.info("获取歌单内所有音乐成功;歌单ID:[{}];共获取该歌单 [{}] 条音乐",gdid,trackIds.size());
  }

  /**
   * 根据歌曲id 返回歌曲详细
   * @param id 歌曲id
   * @return
   */
  public song getSong(String id,String ymdStr){
    log.info("准备获取歌曲 ID:[{}] 的信息",id);
    String getSongUrl = "http://47.99.165.122:3000/song/detail?ids="+id;
    // 创建 异步线程类 获取音乐url
    /*
    class music implements Callable<String>{
      private String id;
      public music (String id){
        this.id = id;
      }
      @Override
      public String call() throws Exception {
        //根据Id 查询音乐UR；
        log.info("开始获取ID: [{}] 的具体音乐URL",this.id);
        String getMusicUrl = "https://musicapi.leanapp.cn/music/url?id="+this.id;
        String jsonStr = httpRequestTools.get(getMusicUrl);
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        String dataStr = jsonObject.get("data").toString();
        //得到音乐url对象
        JSONObject dataObject = JSONObject.fromObject(dataStr.substring(1,dataStr.length() - 1));
        //返回音乐URl
        String url = dataObject.get("url").toString();
        log.info("获取ID: [{}] 的具体音乐URL成功,URL:[{}]",this.id,url);
        return url;
      }
    }
    FutureTask<String> futureTask = new FutureTask<String>(new music(id));
    futureTask.run();

     */
    String songStr = httpRequestTools.get(getSongUrl);
    //得到歌曲json字符串
    String json = JSONObject.fromObject(songStr).get("songs").toString();
    // 因为返回的歌曲json字符串是数组对象，但是只有一条数据 ，需要把数组的[]括号去去掉然后转换具体歌曲 JSONObject对象
    json = json.substring(json.indexOf("[")+1,json.lastIndexOf("]"));
    // 把处理好的歌曲json字符串转换为 JSONObject对象
    JSONObject songObject = JSONObject.fromObject(json);
    // 创建歌曲实体类赋值
    song s = new song();
    /*
    try {
      s.setUrl(futureTask.get());
    }catch(Exception e){
      log.error("获取音乐ID出错，原因[{}]",e.getMessage());
    }
    */


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
    s.setPlayListTimeid(ymdStr);
    log.info("成功获取到音乐详细信息 ,ID:[{}],信息: [{}]",id,s);
    return s;
  }
}
