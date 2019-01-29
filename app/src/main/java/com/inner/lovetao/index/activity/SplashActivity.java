package com.inner.lovetao.index.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.inner.lovetao.R;
import com.inner.lovetao.config.ArouterConfig;
import com.inner.lovetao.config.ConfigInfo;
import com.inner.lovetao.index.mvp.SplashContract;
import com.inner.lovetao.index.mvp.SplashPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DataHelper;

/**
 * desc:启动页
 * Created by xcz
 * on 2019/1/10.
 */
public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View, SplashContract.Model {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.ac_splash;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        new Handler() {
        }.postDelayed(() -> {
            if (TextUtils.isEmpty(DataHelper.getStringSF(this, ConfigInfo.FIRST_INSTALL))) {
                toGuild();
            } else {
                toMain();
            }
            finish();
        }, 2000);

    }

    /**
     * 跳转引导页
     */
    @Override
    public void toGuild() {
        ARouter.getInstance().build(ArouterConfig.AC_GUILD).navigation(this);
    }

    /**
     * 跳转至首页
     */
    @Override
    public void toMain() {
        ARouter.getInstance().build(ArouterConfig.AC_MAIN).navigation(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showMessage(@NonNull String message) {
        ArmsUtils.makeText(this, message);

    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    @Override
    public boolean useFragment() {
        return false;
    }
}
