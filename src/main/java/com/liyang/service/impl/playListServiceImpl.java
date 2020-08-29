package com.liyang.service.impl;

import com.liyang.entity.Playlist;
import com.liyang.mapper.PlaylistMapper;
import com.liyang.service.playListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class playListServiceImpl implements playListService {
  @Autowired
  public PlaylistMapper mapper;
  @Override
  public List<Playlist> queryPlayListAll() {
    System.out.println("mapper"+mapper);
    return mapper.queryPlayListAll();
  }

  @Override
  public int addPlayList(Playlist e) {
    return mapper.addPlayList(e);
  }
}
