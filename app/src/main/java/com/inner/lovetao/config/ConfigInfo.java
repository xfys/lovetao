package com.inner.lovetao.config;

/**
 * desc:配置信息
 * Created by xcz
 * on 2019/1/10.
 */
public interface ConfigInfo {
    String FIRST_INSTALL = "first_install";

    interface ResponseCode {
        //成功获取数据
        String Success = "0000";
        //未登录
        String notLogin = "0001";
        //未授权
        String notAuthTB = "0002";
    }
}
