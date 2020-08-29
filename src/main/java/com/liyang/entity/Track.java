package com.liyang.entity;

/**
 *  歌单与歌曲中间内 用于连接歌单与歌曲之间联系
 */
public class Track {
    private String id; //歌曲ID

    private String v;

    private String alg;

    private String at;
    private String gdId;//歌单ID

    public void setId(String id) {
        this.id = id;
    }

    public void setV(String v) {
        this.v = v;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public void setGdId(String gdId) {
        this.gdId = gdId;
    }

    public String getId() {
        return id;
    }

    public String getV() {
        return v;
    }

    public String getAlg() {
        return alg;
    }

    public String getAt() {
        return at;
    }

    public String getGdId() {
        return gdId;
    }
}