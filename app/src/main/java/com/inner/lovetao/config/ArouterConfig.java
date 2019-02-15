package com.inner.lovetao.config;

/**
 * desc:页面路由配置
 * Created by xcz
 * on 2019/1/29.
 */
public class ArouterConfig {
    //主页面
    public static final String AC_MAIN = "/home/activity/MainActivity";
    //设置页面
    public static final String AC_SETTING = "/settings/ui/activity/SettingActivity";
    //淘宝授权页面
    public static final String AC_TB_AUTH = "/loginregister/mvp/ui/activity/TBLoginActivity";
    //手机号绑定页面
    public static final String AC_BIND_PHONE = "/settings/mvp/ui/activity/SettingActivity";
    //引导页面
    public static final String AC_GUILD = "/index/activity/GuildActivity";
    //意见反馈
    public static final String AC_SUGGEST = "/settings/mvp/ui/activity/SuggestActivity";
    //关于我们
    public static final String AC_ABOUT_US = "/settings/mvp/ui/activity/AboutUsActivity";
    //今日上新
    public static final String AC_SHELVES = "/channel/ui/activity/ShelvesActivity";
    //我的收益
    public static final String AC_MINE_EARNING = "/mineassets/mvp/ui/activity/EarningsActivity";

    public static class Interceptor {
        public static final String LOGIN_INTERCEPT = "login_intercept";
    }
}
