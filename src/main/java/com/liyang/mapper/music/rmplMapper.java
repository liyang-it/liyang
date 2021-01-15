package com.liyang.mapper.music;

import com.liyang.entity.music.rmpl;

public interface rmplMapper {
  int insertRmpl(rmpl y);
  rmpl selectRandRmpl();
}
