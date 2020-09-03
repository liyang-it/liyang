package com.liyang.service.impl;

import com.liyang.entity.Track;
import com.liyang.entity.song;
import com.liyang.mapper.TrackMapper;
import com.liyang.mapper.songMapper;
import com.liyang.service.songService;
import com.liyang.service.trackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class trackServiceImpl implements trackService {
  @Autowired
  public TrackMapper mapper;

  @Override
  public int deleteTrack() {
    return mapper.deleteTrack();
  }

  @Override
  public int addTrack(Track t) {
    return mapper.addTrack(t);

  }
}
