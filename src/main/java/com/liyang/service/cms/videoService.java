package com.liyang.service.cms;


import com.liyang.entity.cms.video;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface videoService {
  // 根据影视名称 分页查询
  List<video> queryVideoByNameAndPage(String name, int page, int limit);
  video queryVideoById(Integer id);
}
