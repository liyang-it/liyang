
package com.liyang.service.music.impl;

import com.liyang.entity.music.pageUrl;
import com.liyang.mapper.music.pageUrlMapper;
import com.liyang.service.music.pageUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class pageUrlServiceImpl implements pageUrlService {
  @Autowired
  public pageUrlMapper pageUrlMapper;

  @Override
  public String queryPageUrl(String dict) {
    return pageUrlMapper.queryPageUrl(dict);
  }

  @Override
  public List<pageUrl> queryAll() {
    return pageUrlMapper.queryAll();
  }

  @Override
  public int updatePageUrl(pageUrl page) {
    return pageUrlMapper.updatePageUrl(page);
  }
}
