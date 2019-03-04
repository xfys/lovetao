package com.inner.lovetao.config;

import java.io.Serializable;

/**
 * desc:用户数据
 * Created by xcz
 * on 2019/1/29.
 */
public class UserInfo implements Serializable {
    public UserInfo() {
    }

    public UserInfo(String nikeNmae, String phoneNumber, boolean alipayStatus, String cookies) {
        this.nikeNmae = nikeNmae;
        this.phoneNumber = phoneNumber;
        this.alipayStatus = alipayStatus;
        this.cookies = cookies;
    }

    private String nikeNmae;
    private String phoneNumber;
    private boolean alipayStatus;
    private String cookies;

    public String getNikeNmae() {
        return nikeNmae;
    }

    public void setNikeNmae(String nikeNmae) {
        this.nikeNmae = nikeNmae;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isAlipayStatus() {
        return alipayStatus;
    }

    public void setAlipayStatus(boolean alipayStatus) {
        this.alipayStatus = alipayStatus;
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "nikeNmae='" + nikeNmae + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", alipayStatus=" + alipayStatus +
                ", cookies='" + cookies + '\'' +
                '}';
    }
}
