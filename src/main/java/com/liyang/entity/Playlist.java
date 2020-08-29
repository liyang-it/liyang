package com.liyang.entity;

import java.util.Date;

public class Playlist extends baseEntity {
    private String id;

    private String name;

    private String picurl;

    private Integer playcount;

    private Integer trackcount;

    private String tracknumberupdatetime;

    private Integer type;

    private String alg;

    private Integer candislike;

    private String copywriter;

    private Integer highquality;

    private String playlisttimeid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl == null ? null : picurl.trim();
    }

    public Integer getPlaycount() {
        return playcount;
    }

    public void setPlaycount(Integer playcount) {
        this.playcount = playcount;
    }

    public Integer getTrackcount() {
        return trackcount;
    }

    public void setTrackcount(Integer trackcount) {
        this.trackcount = trackcount;
    }

    public String getTracknumberupdatetime() {
        return tracknumberupdatetime;
    }

    public void setTracknumberupdatetime(String tracknumberupdatetime) {
        this.tracknumberupdatetime = tracknumberupdatetime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg == null ? null : alg.trim();
    }

    public Integer getCandislike() {
        return candislike;
    }

    public void setCandislike(Integer candislike) {
        this.candislike = candislike;
    }

    public String getCopywriter() {
        return copywriter;
    }

    public void setCopywriter(String copywriter) {
        this.copywriter = copywriter == null ? null : copywriter.trim();
    }

    public Integer getHighquality() {
        return highquality;
    }

    public void setHighquality(Integer highquality) {
        this.highquality = highquality;
    }

    public String getPlaylisttime() {
        return playlisttimeid;
    }

    public void setPlaylisttimeid(String playlisttimeid) {
        this.playlisttimeid = playlisttimeid == null ? null : playlisttimeid.trim();
    }
}