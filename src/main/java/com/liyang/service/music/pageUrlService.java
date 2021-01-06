package com.liyang.service.music;

import com.liyang.entity.music.pageUrl;

import java.util.List;

    public interface pageUrlService {
      // 根据字典查询 状态为1的 url
      String queryPageUrl(String dict);
      // 查询所有
      List<pageUrl> queryAll();
      int updatePageUrl(pageUrl page);
}
