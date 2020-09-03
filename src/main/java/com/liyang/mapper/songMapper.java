package com.liyang.mapper;

import com.liyang.entity.song;
import com.liyang.pojo.music;

import java.util.List;

public interface songMapper {
  int addSong(song s);
  List<song> querySongsPageAll(song s);
  int deleteSong();

  /**
   * 根据歌单ID 查询
   * @param id
   * @return
   */
  List<music> queryMusicAllByGdid(song s);

}