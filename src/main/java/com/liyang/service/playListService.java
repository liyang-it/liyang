package com.liyang.service;

import com.liyang.entity.Playlist;

import java.util.List;


public interface playListService {

  public int addPlayList(Playlist e);
  List<Playlist> queryPlayListPageAll(Playlist e);
  int deletePlayList();
}
