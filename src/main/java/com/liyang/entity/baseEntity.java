package com.liyang.entity;

public class baseEntity {
  private Integer page;
  private Integer limit;
  private String params1;

  public void setParams1(String params1) {
    this.params1 = params1;
  }

  public String getParams1() {
    return params1;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public Integer getLimit() {
    return limit;
  }

  public Integer getPage() {
    return (page -1)*limit;
  }
}
