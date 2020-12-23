package com.liyang.service.impl;

import com.liyang.mapper.systemAsNumberMapper;
import com.liyang.service.systemAsNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class systemAsNumberServiceImpl implements systemAsNumberService {
  @Autowired
  private systemAsNumberMapper systemAsNumberMapper;
  @Override
  public int updateAccessNumber(int number) {
    return systemAsNumberMapper.updateAccessNumber(number);
  }
}
