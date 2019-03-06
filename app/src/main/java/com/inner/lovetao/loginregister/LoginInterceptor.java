package com.inner.lovetao.loginregister;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.inner.lovetao.config.ArouterConfig;
import com.inner.lovetao.config.UserInstance;

/**
 * desc:用户登录拦截器
 * Created by xcz
 * on 2019/1/29.
 */
@Interceptor(priority = 100, name = ArouterConfig.Interceptor.LOGIN_INTERCEPT)
public class LoginInterceptor implements IInterceptor {
    private Context context;

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        String path = postcard.getPath();
        if (UserInstance.getInstance().isLogin(context)) {
            callback.onContinue(postcard);
        } else {
            switch (path) {
                //不需要登录的页面路径
                case ArouterConfig.AC_TB_AUTH:
                case ArouterConfig.AC_GUILD:
                case ArouterConfig.AC_MAIN:
                case ArouterConfig.AC_BIND_PHONE:
                case ArouterConfig.AC_SHELVES:
                case ArouterConfig.AC_ABOUT_US:
                case ArouterConfig.AC_MINE_EARNING:
                case ArouterConfig.AC_EARNING_DETAIL:
                case ArouterConfig.AC_WEBVIEW:
                    callback.onContinue(postcard);
                    break;
                //默认需要登录
                default:
                    callback.onInterrupt(null);
                    ARouter.getInstance().build(ArouterConfig.AC_TB_AUTH).navigation(context);
                    break;
            }

        }
    }

    @Override
    public void init(Context context) {
        this.context = context;
    }
}
