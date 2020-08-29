package com.liyang.mapper;

import com.liyang.entity.song;

import java.util.List;

public interface songMapper {
  int addSong(song s);

  /**
   *  歌单ID 查询歌曲
   * @param gdid
   * @return
   */
  List<song> querySongByGdId(String gdid);

}