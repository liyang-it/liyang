package com.liyang.mapper.music;

import com.liyang.entity.music.pageUrl;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
public interface pageUrlMapper {
  // 根据字典查询 状态为1的 url
  String queryPageUrl(String dict);
  // 查询所有
  List<pageUrl> queryAll();
  int updatePageUrl(pageUrl page);

}
