package com.liyang.task;

import com.liyang.service.systemAsNumberService;
import com.liyang.tools.redisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 定时存储redis数据到数据库
 */
@Component
@EnableScheduling
@Configuration
public class saveAccessNumber {
  @Resource
  public redisUtil redisUtil;
  @Autowired
  public systemAsNumberService systemAsNumberService;
  @Scheduled(cron = "0 55 23 * * ? ")
  public void run(){
    String str = String.valueOf(redisUtil.get("liyangit:accessNumber"));
    Integer accessNumber = Integer.valueOf(str);
    systemAsNumberService.updateAccessNumber(accessNumber);
  }
}
