package com.liyang.entity;

/**
 * 歌曲pojo
 */
public class song extends baseEntity{
  private String id; //歌曲ID
  private String name;  //歌曲名称
  private String alId; //专辑ID
  private String alName;//专辑名称
  private String alPic; //音乐封面id
  private String alPicUrl;//音乐封面地址
  private String arId;//音乐作者ID
  private String arName;//音乐作者名
  private String mvId;//mvid
  private String url;//音乐地址
  private String gdid;
  private String playListTimeid;

  public String getPlayListTimeid() {
    return playListTimeid;
  }

  public void setPlayListTimeid(String playListTimeid) {
    this.playListTimeid = playListTimeid;
  }

  public String getGdid() {
    return gdid;
  }

  public void setGdid(String gdid) {
    this.gdid = gdid;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public String toString() {
    return "song{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", alId='" + alId + '\'' +
            ", alName='" + alName + '\'' +
            ", alPic='" + alPic + '\'' +
            ", alPicUrl='" + alPicUrl + '\'' +
            ", arId='" + arId + '\'' +
            ", arName='" + arName + '\'' +
            ", mvId='" + mvId + '\'' +
            '}';
  }

  public void setAlId(String alId) {
    this.alId = alId;
  }

  public void setAlName(String alName) {
    this.alName = alName;
  }

  public void setAlPic(String alPic) {
    this.alPic = alPic;
  }

  public void setAlPicUrl(String alPicUrl) {
    this.alPicUrl = alPicUrl;
  }

  public void setArId(String arId) {
    this.arId = arId;
  }

  public void setArName(String arName) {
    this.arName = arName;
  }

  public void setMvId(String mvId) {
    this.mvId = mvId;
  }

  public String getAlId() {
    return alId;
  }

  public String getAlName() {
    return alName;
  }

  public String getAlPic() {
    return alPic;
  }

  public String getAlPicUrl() {
    return alPicUrl;
  }

  public String getArId() {
    return arId;
  }

  public String getArName() {
    return arName;
  }

  public String getMvId() {
    return mvId;
  }

  public song() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }



  public String getName() {
    return name;
  }
}
