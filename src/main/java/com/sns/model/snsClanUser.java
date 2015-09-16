package com.sns.model;

import java.util.Date;

public class snsClanUser {
    private Integer id;

    private Integer cid;

    private Integer uid;

    private Byte state;

    private Byte level;

    private Date creattime;

    private Date updatetime;

    private snsClan Clan;

    private snsUser user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public snsClan getClan(){
        return Clan;
    }

    public void setClan(snsClan Clan){
        this.Clan =Clan;
    }

    public snsUser getUser(){
        return user;
    }

    public void setUser(snsUser user){
        this.user=user;
    }
}