package com.inner.lovetao.loginregister.bean;

import java.io.Serializable;

/**
 * desc:淘宝登录参数
 * Created by xcz
 * on 2019/3/10.
 */
public class TbLoginBean implements Serializable {

    /**
     * aliPayAccount : string
     * createTime : 2019-03-09T12:17:54.431Z
     * headPicUrl : string
     * id : 0
     * invitationCode : string
     * nick : string
     * openId : string
     * openSid : string
     * phone : string
     * realName : string
     * state : true
     * topAccessToken : string
     * topAuthCode : string
     * updateTime : 2019-03-09T12:17:54.431Z
     * verifyCode : string
     */

    private String headPicUrl;
    private String nick;
    private String openId;
    private String openSid;


    public String getHeadPicUrl() {
        return headPicUrl;
    }

    public void setHeadPicUrl(String headPicUrl) {
        this.headPicUrl = headPicUrl;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
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

}

