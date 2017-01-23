package com.ittianyu.pocenter.common.bean;

import java.sql.Timestamp;

/**
 * Created by yu on 2017/1/14.
 */
public class ProjectBean {
    public int id;
    public String projectId;
    public String title;
    public String description;
    public String price;
    public int type;
    public String cycle;
    public int people;
    public int status;
    public Timestamp time;
    public String reference;
    public String url;


    @Override
    public String toString() {
        return "ProjectBean{" +
                "projectId='" + projectId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", type=" + type +
                ", cycle='" + cycle + '\'' +
                ", people=" + people +
                ", status=" + status +
                ", time=" + time +
                ", reference='" + reference + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
