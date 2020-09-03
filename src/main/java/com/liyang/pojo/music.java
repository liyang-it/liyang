package com.liyang.pojo;

/**
 *  音乐显示pojo
 */
public class music {
  private String id;//音乐id
  private String title;//音乐名称
  private String alName;//专辑名称
  private String pic;//音乐封面url
  private String artist;//作者
  private String src;//音乐url
  private String mvId;//mvId

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAlName() {
    return alName;
  }

  public void setAlName(String alName) {
    this.alName = alName;
  }

  public String getPic() {
    return pic;
  }

  public void setPic(String pic) {
    this.pic = pic;
  }

  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public String getSrc() {
    return src;
  }

  public void setSrc(String src) {
    this.src = src;
  }

  public String getMvId() {
    return mvId;
  }

  public void setMvId(String mvId) {
    this.mvId = mvId;
  }
}
