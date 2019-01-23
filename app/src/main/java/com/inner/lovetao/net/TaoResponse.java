package com.inner.lovetao.net;

import com.inner.lovetao.config.ConfigInfo;

/**
 * desc:返回数据
 * Created by xcz
 * on 2019/1/23.
 */
public class TaoResponse<T> {
    private String code;
    private String msg;
    private T data;
    private long serverTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getServerTime() {
        return serverTime;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
    }

    public boolean isSuccess() {
        return ConfigInfo.ResponseCode.Success.equals(code);
    }
}
