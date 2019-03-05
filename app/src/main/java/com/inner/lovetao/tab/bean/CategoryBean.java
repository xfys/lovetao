package com.inner.lovetao.tab.bean;

import java.io.Serializable;

/**
 * desc:首页条目
 * Created by xcz
 * on 2019/3/5.
 */
public class CategoryBean implements Serializable {

    /**
     * id : 18
     * name : 健康
     * desc : null
     * createTime : 1551711694000
     * state : true
     * updateTime : 1551711694000
     */

    private int id;
    private String name;
    private Object desc;
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

    public Object getDesc() {
        return desc;
    }

    public void setDesc(Object desc) {
        this.desc = desc;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
