package com.inner.lovetao.config;

import android.content.Context;
import android.text.TextUtils;

import com.jess.arms.utils.DataHelper;

/**
 * desc:用户相关信息单例
 * Created by xcz
 * on 2019/1/29.
 */
public class UserInstance {

    public static UserInstance getInstance() {
        return InnerHolder.INSTANCE;
    }

    private UserInstance() {
    }

    private static class InnerHolder {
        public static final UserInstance INSTANCE = new UserInstance();

    }

    /**
     * @param context
     * @return true:已登录 false：未登录
     */
    public boolean isLogin(Context context) {
        if (getUserInfo(context) != null && !TextUtils.isEmpty(getUserInfo(context).getCookies())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param context
     * @return
     */
    public UserInfo getUserInfo(Context context) {
        return DataHelper.getDeviceData(context, ConfigInfo.USER_INFO);
    }

    /**
     * 缓存用户信息
     *
     * @param context
     * @param userInfo
     */
    public void setUserInfo(Context context, UserInfo userInfo) {
        if (userInfo != null) {
            DataHelper.saveDeviceData(context, ConfigInfo.USER_INFO, userInfo);
        }
    }

    /**
     * 清除用户信息
     *
     * @param context
     */
    public void clearUserInfo(Context context) {
        DataHelper.saveDeviceData(context, ConfigInfo.USER_INFO, null);
    }

}
