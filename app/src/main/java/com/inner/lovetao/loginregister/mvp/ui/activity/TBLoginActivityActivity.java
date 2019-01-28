package com.inner.lovetao.loginregister.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.inner.lovetao.R;
import com.inner.lovetao.loginregister.di.component.DaggerTBLoginActivityComponent;
import com.inner.lovetao.loginregister.mvp.contract.TBLoginActivityContract;
import com.inner.lovetao.loginregister.mvp.presenter.TBLoginActivityPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * desc:淘宝授权登录
 * Created by xcz
 * on 2019/01/28
 */
public class TBLoginActivityActivity extends BaseActivity<TBLoginActivityPresenter> implements TBLoginActivityContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerTBLoginActivityComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_tb_login; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(com.jess.arms.R.mipmap.ic_black_back_arrow);
        }
        if (mToolbarTitle != null) {
            mToolbarTitle.setText("");
        }
        if (getLine() != null) {
            getLine().setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.tv_tb_login, R.id.tv_user_agreement})
    public void onClick(View view) {
        switch (view.getId()) {
            //淘宝登录
            case R.id.tv_tb_login:
                launchActivity(new Intent(this, BindPhoneActivityActivity.class));
                break;
            //用户协议
            case R.id.tv_user_agreement:
                break;
        }

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.makeText(this, message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }
}
