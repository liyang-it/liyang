package com.liyang.entity.music;

public class rmpl {
  private String content;
  private String username;
  private String avatar;
  private String song;

  public rmpl() {
  }

  public rmpl(String content, String username, String avatar, String song) {
    this.content = content;
    this.username = username;
    this.avatar = avatar;
    this.song = song;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getSong() {
    return song;
  }

  public void setSong(String song) {
    this.song = song;
  }
}
