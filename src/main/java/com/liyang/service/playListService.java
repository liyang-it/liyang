package com.liyang.service;

import com.liyang.entity.Playlist;

import java.util.List;


public interface playListService {

  public List<Playlist> queryPlayListAll();
  public int addPlayList(Playlist e);
}
