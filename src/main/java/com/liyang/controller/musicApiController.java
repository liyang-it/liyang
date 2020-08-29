package com.liyang.controller;

import com.alibaba.fastjson.JSON;
import com.liyang.pojo.track;
import com.liyang.tools.httpRequestTools;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 音乐api
 */
@RestController
@RequestMapping("/music")

public class musicApiController extends baseController{
  /**
   * 根据 歌单ID 获取歌单下的音乐列表信息
   * @param id
   * @return
   */
  @RequestMapping(value = "/getSongs.json")
  @ResponseBody
  public String getSongs(String id){
    if(id == null || "".equals(id)) {
      return "参数错误";
    }else{
        String url = "https://musicapi.leanapp.cn/playlist/detail?id=" + id;
        //得到歌单中的音乐列表ID
        String str = httpRequestTools.get(url);
        JSONObject jsonObject = JSONObject.fromObject(str);
        //根据音乐ID获取具体音乐信息
        JSONObject gqList = JSONObject.fromObject(jsonObject.get("playlist"));
        //获取歌曲id集合
        List<track> tList = JSON.parseArray(gqList.get("trackIds").toString(),track.class);
        //拿到所有id集合 重新发起请求获取歌曲详细信息
        System.out.println("歌曲id数量" + tList.size());
        String pjStr = "[";
        StringBuilder sb = new StringBuilder("[");
        for(int i =0; i<tList.size();i++){
          String songUrl = "https://musicapi.leanapp.cn/song/detail?ids=" + tList.get(i).getId();
          String str2 = httpRequestTools.get(songUrl);
          JSONObject jsonObject2 = JSONObject.fromObject(str2);
          // 音乐
          String sbStr = jsonObject2.get("songs").toString();
          sbStr = sbStr.substring(sbStr.indexOf("[")+1,sbStr.length()-1);
          sb.append(sbStr);
          sb.append(",");
       }
        return sb.toString();

      }

  }


}
