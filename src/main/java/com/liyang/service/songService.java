package com.liyang.service;

import com.liyang.entity.song;
import com.liyang.pojo.music;

import java.util.List;


public interface songService {

  int addSong(song s);
  List<song> querySongsPageAll(song s);
  int deleteSong();
  List<music> queryMusicAllByGdid(song s);
}
