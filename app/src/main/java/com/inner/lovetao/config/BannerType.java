package com.inner.lovetao.config;

/**
 * desc:banner接口配置
 * Created by xcz
 * on 2019/3/15.
 */
public enum BannerType {
    HOME_BANNER(1),//banner
    HMOE_NAVIGATION(2),//邀请收徒，每日图片
    POP_WIN(3),
    MINE_PROMOTION(4),//我的页面广告位
    WLF_VIP(5),
    WLF_MORE(6);

    private final int type;

    BannerType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
