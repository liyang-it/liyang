package com.liyang.entity.music;

import javax.validation.constraints.NotNull;

public class pageUrl{
  @NotNull(message = "ID不能为空")
  private Integer id;
  private String dict;
  private String url;
  @NotNull(message = "status不能为空")
  private Integer status;

  public pageUrl( Integer id, Integer status) {
    this.id = id;
    this.status = status;
  }

  public pageUrl() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDict() {
    return dict;
  }

  public void setDict(String dict) {
    this.dict = dict;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
