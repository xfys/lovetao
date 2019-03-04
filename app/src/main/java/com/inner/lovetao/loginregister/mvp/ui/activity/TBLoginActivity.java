package com.inner.lovetao.loginregister.mvp.ui.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ali.auth.third.core.MemberSDK;
import com.ali.auth.third.core.callback.LoginCallback;
import com.ali.auth.third.core.model.Session;
import com.ali.auth.third.login.LoginService;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.inner.lovetao.R;
import com.inner.lovetao.config.ArouterConfig;
import com.inner.lovetao.core.TaoResponse;
import com.inner.lovetao.loginregister.di.component.DaggerTBLoginActivityComponent;
import com.inner.lovetao.loginregister.mvp.contract.TBLoginActivityContract;
import com.inner.lovetao.loginregister.mvp.presenter.TBLoginActivityPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;

import javax.inject.Inject;

import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * desc:淘宝授权登录
 * Created by xcz
 * on 2019/01/28
 */
@Route(path = ArouterConfig.AC_TB_AUTH)
public class TBLoginActivity extends BaseActivity<TBLoginActivityPresenter> implements TBLoginActivityContract.View {

    @Inject
    Dialog mDialog;

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
//                toTBAuth();
                mPresenter.getTestData(0);
                break;
            //用户协议
            case R.id.tv_user_agreement:
                break;
        }

    }

    /**
     * 淘宝授权认证
     */
    private void toTBAuth() {
        LoginService loginService = MemberSDK.getService(LoginService.class);
        loginService.auth(new LoginCallback() {
            @Override
            public void onSuccess(Session session) {
                showMessage("授权成功");
                LogUtils.debugInfo("TBAuth--->session:" + session.toString());
            }

            @Override
            public void onFailure(int code, String message) {
                showMessage("授权取消");
                LogUtils.debugInfo("TBAuth--->code:" + code + ";msg:" + message);
            }
        });
    }

    @Override
    public void showLoading() {
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        mDialog.dismiss();
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.makeText(this, message);
    }


    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void getDatasu(TaoResponse<String> taoResponse) {
        showMessage(taoResponse.getMessage());
    }

    @Override
    public AppCompatActivity getActivity() {
        return this;
    }
}
