package com.inner.lovetao.tab.bean;

import java.io.Serializable;

/**
 * desc:
 * Created by xcz
 * on 2019/1/22.
 */
public class TestItemBean implements Serializable {
    private String name;
    private String tbPrise;
    private String already;
    private String quanAferPrice;
    private String quanPrise;

    public TestItemBean(String name, String tbPrise, String already, String quanAferPrice, String quanPrise) {
        this.name = name;
        this.tbPrise = tbPrise;
        this.already = already;
        this.quanAferPrice = quanAferPrice;
        this.quanPrise = quanPrise;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTbPrise() {
        return tbPrise;
    }

    public void setTbPrise(String tbPrise) {
        this.tbPrise = tbPrise;
    }

    public String getAlready() {
        return already;
    }

    public void setAlready(String already) {
        this.already = already;
    }

    public String getQuanAferPrice() {
        return quanAferPrice;
    }

    public void setQuanAferPrice(String quanAferPrice) {
        this.quanAferPrice = quanAferPrice;
    }

    public String getQuanPrise() {
        return quanPrise;
    }

    public void setQuanPrise(String quanPrise) {
        this.quanPrise = quanPrise;
    }
}
