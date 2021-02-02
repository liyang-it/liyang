package com.liyang.service.cms.impl;

import com.liyang.entity.cms.video;
import com.liyang.mapper.cms.videoMapper;
import com.liyang.service.cms.videoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class videoServiceImpl implements videoService {
  @Autowired
  private videoMapper videoMapper;

  @Override
  public List<video> queryVideoByNameAndPage(String name, int page, int limit) {
    return videoMapper.queryVideoByNameAndPage(name,page,limit);
  }
}
