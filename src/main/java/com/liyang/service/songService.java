package com.liyang.service;

import com.liyang.entity.song;

import java.util.List;


public interface songService {

  int addSong(song s);

  /**
   *  歌单ID 查询歌曲
   * @param gdid
   * @return
   */
  List<song> querySongByGdId(String gdid);
}
