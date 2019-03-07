package com.inner.lovetao.tab.bean;

import java.io.Serializable;

/**
 * 商品item
 */
public class ProductItemBean implements Serializable {
    /**
     * id : 7
     * couponAmount : 3
     * couponSrcScene : 1
     * couponType : 0
     * couponActivityId : 41f19f0b7b8043628be8c55c67cf176e
     * numIid : 587478525095
     * couponRemainCount : 2000
     * couponTotalCount : 10000
     * couponEndTime : 1551542400000
     * couponStartTime : 1551542400000
     * couponStartFee : 4.0
     * smallImages : http://img.alicdn.com/tfscom/i1/1932700228/O1CN01OcMnUW1DYSnmk63xN_!!1932700228.jpg
     * commissionRate : 5.22
     * couponClickUrl : https://uland.taobao.com/coupon/edetail?e=TEU6QXZOGqYGQASttHIRqZUw8jW7ecHIz%2FRrvrpKXyHGVWvbOqykrrM7hr0cpBDkC8DuvSRYDjifdQyFDmpGON32UcDfNpkAm4VLH9mslwwbzU%2BHnawCuvKMLsm6yjETYZnLG%2BGHT7m30OV5ZzvumwMFTfGLA%2Fdy11S084xSU%2BXiYslmRTfxHBdOOR5C4gXnQS0Flu%2FfbSog%2BeE%2BjpQFGFLajOROSSHlO7rndFRDLgjwJvRRhZCJ%2FQ%3D%3D&traceId=0b01f67a15516192014402394e
     * itemDescription :
     * category : 16
     * couponInfo : 满4元减3元
     * itemUrl : https://detail.tmall.com/item.htm?id=587478525095
     * pictUrl : http://img.alicdn.com/tfscom/i4/1932700228/O1CN01qfMmah1DYSnp8h94b_!!1932700228.jpg
     * volume : 22
     * sellerId : 1932700228
     * nick : 超会购
     * title : 女春2019新款韩版气质百搭蕾丝上衣+高腰显瘦chic格子裤分开拍潮
     * zkFinalPrice : 29.98
     * userType : true
     * state : true
     * shopTitle : 九郡主潮妹馆
     * categoryId : 2
     * createTime : 1551619226000
     * updateTime : 1551619226000
     */

    private int id;
    private int couponAmount;
    private int couponSrcScene;
    private int couponType;
    private String couponActivityId;
    private long numIid;
    private int couponRemainCount;
    private int couponTotalCount;
    private long couponEndTime;
    private long couponStartTime;
    private String couponStartFee;
    private String smallImages;
    private String commissionRate;
    private String couponClickUrl;
    private String itemDescription;
    private int category;
    private String couponInfo;
    private String itemUrl;
    private String pictUrl;
    private int volume;
    private String sellerId;
    private String nick;
    private String title;
    private String zkFinalPrice;
    private boolean userType;
    private boolean state;
    private String shopTitle;
    private String categoryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(int couponAmount) {
        this.couponAmount = couponAmount;
    }

    public int getCouponSrcScene() {
        return couponSrcScene;
    }

    public void setCouponSrcScene(int couponSrcScene) {
        this.couponSrcScene = couponSrcScene;
    }

    public int getCouponType() {
        return couponType;
    }

    public void setCouponType(int couponType) {
        this.couponType = couponType;
    }

    public String getCouponActivityId() {
        return couponActivityId;
    }

    public void setCouponActivityId(String couponActivityId) {
        this.couponActivityId = couponActivityId;
    }

    public long getNumIid() {
        return numIid;
    }

    public void setNumIid(long numIid) {
        this.numIid = numIid;
    }

    public int getCouponRemainCount() {
        return couponRemainCount;
    }

    public void setCouponRemainCount(int couponRemainCount) {
        this.couponRemainCount = couponRemainCount;
    }

    public int getCouponTotalCount() {
        return couponTotalCount;
    }

    public void setCouponTotalCount(int couponTotalCount) {
        this.couponTotalCount = couponTotalCount;
    }

    public long getCouponEndTime() {
        return couponEndTime;
    }

    public void setCouponEndTime(long couponEndTime) {
        this.couponEndTime = couponEndTime;
    }

    public long getCouponStartTime() {
        return couponStartTime;
    }

    public void setCouponStartTime(long couponStartTime) {
        this.couponStartTime = couponStartTime;
    }

    public String getCouponStartFee() {
        return couponStartFee;
    }

    public void setCouponStartFee(String couponStartFee) {
        this.couponStartFee = couponStartFee;
    }

    public String getSmallImages() {
        return smallImages;
    }

    public void setSmallImages(String smallImages) {
        this.smallImages = smallImages;
    }

    public String getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(String commissionRate) {
        this.commissionRate = commissionRate;
    }

    public String getCouponClickUrl() {
        return couponClickUrl;
    }

    public void setCouponClickUrl(String couponClickUrl) {
        this.couponClickUrl = couponClickUrl;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getCouponInfo() {
        return couponInfo;
    }

    public void setCouponInfo(String couponInfo) {
        this.couponInfo = couponInfo;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public String getPictUrl() {
        return pictUrl;
    }

    public void setPictUrl(String pictUrl) {
        this.pictUrl = pictUrl;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getZkFinalPrice() {
        return zkFinalPrice;
    }

    public void setZkFinalPrice(String zkFinalPrice) {
        this.zkFinalPrice = zkFinalPrice;
    }

    public boolean isUserType() {
        return userType;
    }

    public void setUserType(boolean userType) {
        this.userType = userType;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getShopTitle() {
        return shopTitle;
    }

    public void setShopTitle(String shopTitle) {
        this.shopTitle = shopTitle;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
