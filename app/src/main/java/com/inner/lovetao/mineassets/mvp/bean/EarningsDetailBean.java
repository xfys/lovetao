package com.inner.lovetao.mineassets.mvp.bean;

import java.io.Serializable;

/**
 * Description:
 * <p>
 * Created by feihaokui on 2019-02-18.
 */
public class EarningsDetailBean implements Serializable {
    private String name;
    private String date;
    private String money;

    public EarningsDetailBean(String name, String date, String money) {
        this.name = name;
        this.date = date;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getMoney() {
        return money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
