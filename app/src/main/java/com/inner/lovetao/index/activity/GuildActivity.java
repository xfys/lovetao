package com.inner.lovetao.index.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.inner.lovetao.R;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

/**
 * desc:引导页面
 * Created by xcz
 * on 2019/1/10.
 */
public class GuildActivity extends BaseActivity {
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.ac_guilde;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
