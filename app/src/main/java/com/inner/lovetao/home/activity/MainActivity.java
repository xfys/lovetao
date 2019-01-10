package com.inner.lovetao.home.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.inner.lovetao.R;
import com.inner.lovetao.home.mvp.MainContract;
import com.inner.lovetao.home.mvp.MainPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.Model, MainContract.View {

    private long mExitTime;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showMessage(@NonNull String message) {
        ArmsUtils.makeText(this, message);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean useFragment() {
        return true;
    }

    @Override
    public boolean useEventBus() {
        return true;
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mExitTime > 3500) {
            showMessage(getString(R.string.str_press_again));
            mExitTime = System.currentTimeMillis();
            return;
        }
        ArmsUtils.exitApp();
    }
}
