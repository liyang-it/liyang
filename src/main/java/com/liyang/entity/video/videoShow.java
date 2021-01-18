package com.liyang.entity.video;

public class videoShow {
  private int id;
  private int show;

  public videoShow() {
  }

  public videoShow(int id, int show) {
    this.id = id;
    this.show = show;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getShow() {
    return show;
  }

  public void setShow(int show) {
    this.show = show;
  }
}
