package com.inner.lovetao.config;

import java.io.Serializable;

/**
 * desc:用户数据
 * Created by xcz
 * on 2019/1/29.
 */
public class UserInfo implements Serializable {


    /**
     * id : 1
     * phone : null
     * verifyCode : null
     * nick : tb148670_99
     * headPicUrl : https://wwc.alicdn.com/avatar/getAvatar.do?userIdStr=P8cYPG*IXmkLPk7zPCNLPklzvCPIXH8YvFlhMFkuvmvT&width=160&height=160&type=sns
     * openId : AAFCVxkEAHuBUNAB21WElbRi
     * openSid : ca47aacc2c246f9021b92a0326b1e6b5ce0c53ebf81f4d014e522e29e8bd9ef093173446fa468f9cd63a28d3af060cbd
     * topAccessToken : null
     * topAuthCode : null
     * realName : null
     * aliPayAccount : null
     * createTime : 1552618475000
     * updateTime : 1552618475000
     * state : true
     * invitationCode : null
     * shareCode : null
     */

    private String id;
    private String phone;
    private String verifyCode;
    private String nick;
    private String headPicUrl;
    private String openId;
    private String openSid;
    private String realName;
    private String aliPayAccount;
    private boolean state;
    private String invitationCode;
    private String shareCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getHeadPicUrl() {
        return headPicUrl;
    }

    public void setHeadPicUrl(String headPicUrl) {
        this.headPicUrl = headPicUrl;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOpenSid() {
        return openSid;
    }

    public void setOpenSid(String openSid) {
        this.openSid = openSid;
    }


    public Object getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Object getAliPayAccount() {
        return aliPayAccount;
    }

    public void setAliPayAccount(String aliPayAccount) {
        this.aliPayAccount = aliPayAccount;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getShareCode() {
        return shareCode;
    }

    public void setShareCode(String shareCode) {
        this.shareCode = shareCode;
    }
}
