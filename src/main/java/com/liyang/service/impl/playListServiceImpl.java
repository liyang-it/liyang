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
  public int addPlayList(Playlist e) {
    return mapper.addPlayList(e);
  }

  @Override
  public List<Playlist> queryPlayListPageAll(Playlist e) {
    return mapper.queryPlayListPageAll(e);
  }

  @Override
  public int deletePlayList() {
    return mapper.deletePlayList();
  }
}
