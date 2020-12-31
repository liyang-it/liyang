
package com.liyang.service.music.impl;
import com.liyang.mapper.music.controlMapper;
import com.liyang.service.music.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class controlServiceImpl implements controlService {
  @Autowired
  public controlMapper controlMapper;
  @Override
  public int queryIsToGd() {
    return controlMapper.queryIsToGd();
  }

  @Override
  public int setIsToGd(Integer param) {
    return controlMapper.setIsToGd(param);
  }
}
