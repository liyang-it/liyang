package com.liyang.entity;

import java.util.Date;

public class Playlisttime {
    private String id;

    private Date playlistcreatedate;
    public Playlisttime(String id){
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getPlaylistcreatedate() {
        return playlistcreatedate;
    }

    public void setPlaylistcreatedate(Date playlistcreatedate) {
        this.playlistcreatedate = playlistcreatedate;
    }
}