package com.inner.lovetao.tab.bean;

import java.io.Serializable;

/**
 * desc:首页banner
 * Created by xcz
 * on 2019/3/4.
 */
public class BannerBean implements Serializable {

    /**
     * id : 1
     * imgUrl : http://
     * contentUrl : http://
     * activityId : null
     * title : Banner
     * level : 1
     * locationType : 1
     * desc : null
     * loginState : 0
     * state : 1
     * createTime : 1551591451000
     * updateTime : 1551591451000
     */

    private int id;
    private String imgUrl;
    private String contentUrl;
    private Object activityId;
    private String title;
    private int level;
    private int locationType;
    private Object desc;
    private int loginState;
    private int state;
    private long createTime;
    private long updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public Object getActivityId() {
        return activityId;
    }

    public void setActivityId(Object activityId) {
        this.activityId = activityId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLocationType() {
        return locationType;
    }

    public void setLocationType(int locationType) {
        this.locationType = locationType;
    }

    public Object getDesc() {
        return desc;
    }

    public void setDesc(Object desc) {
        this.desc = desc;
    }

    public int getLoginState() {
        return loginState;
    }

    public void setLoginState(int loginState) {
        this.loginState = loginState;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
