package com.liyang.service.impl;

import com.liyang.mapper.songMapper;
import com.liyang.entity.song;
import com.liyang.service.songService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements songService {
  @Autowired
  public songMapper mapper;

  @Override
  public int addSong(song s) {
    return mapper.addSong(s);
  }

  @Override
  public List<song> querySongByGdId(String gdid) {
    return mapper.querySongByGdId(gdid);
  }
}
