package com.liyang.service.music.impl;

import com.liyang.entity.music.rmpl;
import com.liyang.mapper.music.rmplMapper;
import com.liyang.service.music.rmplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class RmplServiceImpl implements rmplService {
  @Autowired
  private rmplMapper mapper;

  @Override
  public int insertRmpl(rmpl y) {
    return mapper.insertRmpl(y);
  }

  @Override
  public rmpl selectRandRmpl() {
    return mapper.selectRandRmpl();
  }
}
