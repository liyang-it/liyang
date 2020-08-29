package com.liyang.pojo;

/**
 * 歌曲pojo 用于json操作
 */
public class track {
  private int id;
  private int v;
  private int at;
  private String alg;
  public track(){}
  public track(int id,int v,int at,String alg){
    this.id = id;
    this.v= v;
    this.at = at;
    this.alg=alg;
  }
  public void setAlg(String alg) {
    this.alg = alg;
  }

  public void setAt(int at) {
    this.at = at;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setV(int v) {
    this.v = v;
  }

  public int getAt() {
    return at;
  }

  public int getId() {
    return id;
  }

  public int getV() {
    return v;
  }

  public String getAlg() {
    return alg;
  }
}
