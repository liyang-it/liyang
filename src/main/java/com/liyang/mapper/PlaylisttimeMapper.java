package com.liyang.mapper;

import com.liyang.entity.Playlisttime;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PlaylisttimeMapper {
  public int addPlayListTime(String s);
  int deletePlayListTime();
}