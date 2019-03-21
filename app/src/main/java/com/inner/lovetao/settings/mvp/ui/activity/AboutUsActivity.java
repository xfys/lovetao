package com.inner.lovetao.settings.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.inner.lovetao.BuildConfig;
import com.inner.lovetao.R;
import com.inner.lovetao.config.ArouterConfig;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bmob.v3.update.BmobUpdateAgent;

/**
 * desc:关于我们
 * Created by xcz
 * on 2019/2/14.
 */
@Route(path = ArouterConfig.AC_ABOUT_US)
public class AboutUsActivity extends BaseActivity {
    /**
     * 版本号
     */
    @BindView(R.id.tv_version)
    TextView tvVersion;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_about_us;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        tvVersion.setText("版本号： v" + BuildConfig.VERSION_NAME);
    }

    @OnClick({R.id.tv_version})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_version:
                BmobUpdateAgent.forceUpdate(this);
                break;
        }
    }
}
