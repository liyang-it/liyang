package com.liyang;

import com.alibaba.fastjson.JSON;
import com.liyang.entity.Playlist;
import com.liyang.entity.Track;
import com.liyang.service.*;
import com.liyang.task.TaskUpdateMusic;
import com.liyang.tools.httpRequestTools;
import com.liyang.tools.redisUtil;
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
  @Test
  public void contextLoads() {
    String str = String.valueOf(redisUtil.get("liyangit:accessNumber"));
    Integer accessNumber = Integer.valueOf(str);
    System.out.println(accessNumber);
    systemAsNumberService.updateAccessNumber(accessNumber);
  }

}
