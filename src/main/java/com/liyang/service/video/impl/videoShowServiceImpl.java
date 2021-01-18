package com.liyang.service.video.impl;

import com.liyang.mapper.video.videoShowMapper;
import com.liyang.service.video.videoShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class videoShowServiceImpl implements videoShowService {
  @Autowired
  private videoShowMapper videoShowMapper;

  @Override
  public int selectShow() {
    return videoShowMapper.selectShow();
  }

  @Override
  public int disableShow() {
    return videoShowMapper.disableShow();
  }

  @Override
  public int enableShow() {
    return videoShowMapper.enableShow();
  }
}
