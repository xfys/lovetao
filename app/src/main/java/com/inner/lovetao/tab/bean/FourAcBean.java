package com.inner.lovetao.tab.bean;

import java.io.Serializable;

/**
 * desc: 四个模块
 * Created by xcz
 * on 2019/3/6.
 */
public class FourAcBean implements Serializable {

    /**
     * id : 5
     * name : 精选好物
     * picUrl : 2
     * state : true
     * createTime : 1551704366000
     * updateTime : 1551704366000
     */

    private int id;
    private String name;
    private String picUrl;
    private boolean state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
