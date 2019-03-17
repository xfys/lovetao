package com.inner.lovetao.search.bean;

/*
 *
 *
 * 作 者 :YangFan
 *
 * 版 本 :1.0
 *
 * 创建日期 :2019/1/14      17:41
 *
 * 描 述 :搜索历史ItemBean
 *
 * 修订日期 :
 */

import java.io.Serializable;

public class SearchHistoryItemBean implements Serializable {
    public SearchHistoryItemBean(String desc) {
        this.desc = desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
