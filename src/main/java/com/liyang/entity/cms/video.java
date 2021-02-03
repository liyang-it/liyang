package com.liyang.entity.cms;

public class video {
  private Integer id;
  private String name;
  private String sub;
  private String type;
  private String pic;
  private String actor;
  private String remarks;
  private String area;
  private String lang;
  private String year;
  private String content;
  private String playUrl;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSub() {
    return sub;
  }

  public void setSub(String sub) {
    this.sub = sub;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getPic() {
    return pic;
  }

  public void setPic(String pic) {
    this.pic = pic;
  }

  public String getActor() {
    return actor;
  }

  public void setActor(String actor) {
    this.actor = actor;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getLang() {
    return lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getPlayUrl() {
    return playUrl;
  }

  public void setPlayUrl(String playUrl) {
    this.playUrl = playUrl;
  }

  public video(Integer id, String name, String sub, String type, String pic, String actor, String remarks, String area, String lang, String year, String content, String playUrl) {
    this.id = id;
    this.name = name;
    this.sub = sub;
    this.type = type;
    this.pic = pic;
    this.actor = actor;
    this.remarks = remarks;
    this.area = area;
    this.lang = lang;
    this.year = year;
    this.content = content;
    this.playUrl = playUrl;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public video() {
  }
}
