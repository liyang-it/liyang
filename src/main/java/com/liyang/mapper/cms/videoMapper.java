package com.liyang.mapper.cms;

import com.liyang.entity.cms.video;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface videoMapper {
  // 根据影视名称 分页查询
  List<video> queryVideoByNameAndPage(@Param("name") String name,@Param("page") int page,@Param("limit") int limit);
  // 影视id查询详细
  video queryVideoById(@Param("id") Integer id);
}
