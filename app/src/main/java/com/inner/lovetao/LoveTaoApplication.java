package com.inner.lovetao;

import com.ali.auth.third.core.MemberSDK;
import com.ali.auth.third.core.callback.InitResultCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.base.BaseApplication;
import com.jess.arms.utils.LogUtils;

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
    }
}
