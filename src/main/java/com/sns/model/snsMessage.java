package com.sns.model;

import java.util.Date;

public class snsMessage {
    private Integer id;

    private Byte type;

    private Byte stype;

    private Integer uid;

    private Integer fid;

    private String name;

    private String title;

    private String message;

    private Byte islook;

    private Date creattime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Byte getStype() {
        return stype;
    }

    public void setStype(Byte stype) {
        this.stype = stype;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Byte getIslook() {
        return islook;
    }

    public void setIslook(Byte islook) {
        this.islook = islook;
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }
}