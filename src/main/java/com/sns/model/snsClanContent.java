package com.sns.model;

import java.util.Date;

public class snsClanContent {
    private Integer id;

    private Integer cid;

    private Integer uid;

    private String content;

    private Date creattime;

    private snsClan Clan;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public snsClan getClan(){
        return Clan;
    }

    public void setClan(snsClan Clan){
        this.Clan =Clan;
    }
}