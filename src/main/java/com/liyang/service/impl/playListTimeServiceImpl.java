package com.liyang.service.impl;

import com.liyang.entity.Playlisttime;
import com.liyang.mapper.PlaylisttimeMapper;
import com.liyang.service.playListTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class playListTimeServiceImpl implements playListTimeService {
  @Autowired
  public PlaylisttimeMapper mapper;
  @Override
  public int addPlayListTime(String s) {
    return mapper.addPlayListTime(s);
  }
}
