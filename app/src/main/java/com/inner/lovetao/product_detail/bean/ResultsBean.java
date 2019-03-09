package com.inner.lovetao.product_detail.bean;

import java.util.List;

/**
 * desc:
 * Created by xcz
 * on 2019/3/9.
 */
class ResultsBean {

    /**
     * catLeafName : 低帮鞋
     * catName : 女鞋
     * freeShipment : null
     * hGoodRate : null
     * hPayRate30 : null
     * iRfdRate : null
     * isPrepay : null
     * itemUrl : https://detail.m.tmall.com/item.htm?id=585773453549
     * materialLibType : 1
     * nick : 茵颜鞋类旗舰店
     * numIid : 585773453549
     * pictUrl : https://img.alicdn.com/bao/uploaded/i4/3438410117/O1CN015tpPNU1CjcpdJRTyE_!!0-item_pic.jpg
     * provcity : 浙江 温州
     * ratesum : null
     * reservePrice : 379
     * sellerId : 3438410117
     * shopDsr : null
     * smallImages : ["https://img.alicdn.com/i2/3438410117/O1CN01pOB6yM1Cjcpgd0Ry4_!!3438410117.jpg","https://img.alicdn.com/i3/3438410117/O1CN01AFXeoY1CjcpeOWnmZ_!!3438410117.jpg","https://img.alicdn.com/i2/3438410117/O1CN01SAsQAV1CjcpgB9cIt_!!3438410117.jpg","https://img.alicdn.com/i3/3438410117/O1CN01Cq6P9H1CjcpdeoBLB_!!3438410117.jpg"]
     * title : 小白鞋2019春新款韩版休闲运动鞋ins超火网红小熊鞋女百搭老爹鞋
     * userType : 1
     * volume : 1808
     * zkFinalPrice : 78.9
     */

    private String catLeafName;
    private String catName;
    private String itemUrl;
    private String materialLibType;
    private String nick;
    private long numIid;
    private String pictUrl;
    private String provcity;
    private String reservePrice;
    private long sellerId;
    private String title;
    private int userType;
    private int volume;
    private String zkFinalPrice;
    private List<String> smallImages;

    public String getCatLeafName() {
        return catLeafName;
    }

    public void setCatLeafName(String catLeafName) {
        this.catLeafName = catLeafName;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public String getMaterialLibType() {
        return materialLibType;
    }

    public void setMaterialLibType(String materialLibType) {
        this.materialLibType = materialLibType;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public long getNumIid() {
        return numIid;
    }

    public void setNumIid(long numIid) {
        this.numIid = numIid;
    }

    public String getPictUrl() {
        return pictUrl;
    }

    public void setPictUrl(String pictUrl) {
        this.pictUrl = pictUrl;
    }

    public String getProvcity() {
        return provcity;
    }

    public void setProvcity(String provcity) {
        this.provcity = provcity;
    }

    public String getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(String reservePrice) {
        this.reservePrice = reservePrice;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getZkFinalPrice() {
        return zkFinalPrice;
    }

    public void setZkFinalPrice(String zkFinalPrice) {
        this.zkFinalPrice = zkFinalPrice;
    }

    public List<String> getSmallImages() {
        return smallImages;
    }

    public void setSmallImages(List<String> smallImages) {
        this.smallImages = smallImages;
    }
}
