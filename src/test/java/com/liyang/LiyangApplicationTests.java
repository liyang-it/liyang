package com.liyang;

import com.alibaba.fastjson.JSON;
import com.liyang.entity.Playlist;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

  @Test
  public void contextLoads() {

  }

}
