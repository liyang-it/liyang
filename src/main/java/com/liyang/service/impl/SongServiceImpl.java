package com.liyang.service.impl;

import com.liyang.mapper.songMapper;
import com.liyang.entity.song;
import com.liyang.pojo.music;
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
  public List<song> querySongsPageAll(song s) {
    return mapper.querySongsPageAll(s);
  }

  @Override
  public int deleteSong() {
    return mapper.deleteSong();
  }

  @Override
  public List<music> queryMusicAllByGdid(song s) {
    return mapper.queryMusicAllByGdid(s);
  }
}
