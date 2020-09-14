package com.inner.lovetao;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.ali.auth.third.core.MemberSDK;
import com.ali.auth.third.core.callback.InitResultCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeInitCallback;
import com.inner.lovetao.share.ShareUtils;
import com.jess.arms.base.BaseApplication;
import com.jess.arms.utils.LogUtils;

import cn.jpush.android.api.JPushInterface;

/**
 * desc: Application
 * Created by xcz
 * on 2019/1/10.
 */
public class LoveTaoApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        initARouter();
        initTBAuth();
        initWX();
        initJpush();
    }

    /**
     * 初始化极光
     */
    private void initJpush() {
        JPushInterface.setDebugMode(BuildConfig.DEBUG);
        JPushInterface.init(this);
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }

    /**
     * 初始化路由框架
     */
    private void initARouter() {
        if (BuildConfig.DEBUG) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
    }

    /**
     * 初始化淘宝授权SDK
     */
    private void initTBAuth() {
        MemberSDK.init(this, new InitResultCallback() {

            @Override
            public void onFailure(int code, String msg) {
                LogUtils.debugInfo("TBAuthInit--->code:" + code + ";  msg:" + msg);
            }

            @Override
            public void onSuccess() {
                LogUtils.debugInfo("TBAuthInit--->Success");
            }
        });
        AlibcTradeSDK.asyncInit(this, new AlibcTradeInitCallback() {
            @Override
            public void onSuccess() {
                //初始化成功，设置相关的全局配置参数

                // ...
            }

            @Override
            public void onFailure(int code, String msg) {
                //初始化失败，可以根据code和msg判断失败原因，详情参见错误说明
                LogUtils.debugInfo("TBAuthInit--->code:" + code + ";  msg:" + msg);
            }
        });
    }

    /**
     * 初始化微信
     */
    private void initWX() {
        ShareUtils.getInstance().regToWx(this);
    }
}
