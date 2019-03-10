package com.inner.lovetao.core;

import com.inner.lovetao.config.ConfigInfo;

/**
 * desc:返回数据
 * Created by xcz
 * on 2019/1/23.
 */
public class TaoResponse<T> {
    private String code;
    private String message;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public boolean isSuccess() {
        return ConfigInfo.ResponseCode.Success.equals(code);
    }

    @Override
    public String toString() {
        return "TaoResponse{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
