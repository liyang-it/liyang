package com.liyang.mapper;

import com.liyang.entity.Playlist;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlaylistMapper {
  public List<Playlist> queryPlayListAll();
  public int addPlayList(Playlist e);
  List<Playlist> queryPlayListPageAll(Playlist e);
  int deletePlayList();

}