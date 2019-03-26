package com.inner.lovetao.loginregister.mvp.ui.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;

import com.ali.auth.third.core.MemberSDK;
import com.ali.auth.third.core.callback.LoginCallback;
import com.ali.auth.third.core.model.Session;
import com.ali.auth.third.login.LoginService;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.inner.lovetao.R;
import com.inner.lovetao.config.ArouterConfig;
import com.inner.lovetao.config.UserInfo;
import com.inner.lovetao.config.UserInstance;
import com.inner.lovetao.core.TaoResponse;
import com.inner.lovetao.loginregister.bean.TbLoginBean;
import com.inner.lovetao.loginregister.di.component.DaggerTBLoginActivityComponent;
import com.inner.lovetao.loginregister.mvp.contract.TBLoginActivityContract;
import com.inner.lovetao.loginregister.mvp.presenter.TBLoginActivityPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.LogUtils;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

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
                toTBAuth();
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
                TbLoginBean loginBean = new TbLoginBean();
                loginBean.setNick(session.nick);
                loginBean.setOpenId(session.openId);
                loginBean.setOpenSid(session.openSid);
                loginBean.setHeadPicUrl(session.avatarUrl);
                mPresenter.syncUser(loginBean);
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
    public void syncUserSu(TaoResponse<UserInfo> taoResponse) {
        showMessage("授权成功");
        killMyself();
        if (taoResponse != null && taoResponse.getData() != null) {
            UserInstance.getInstance().setUserInfo(this, taoResponse.getData());
            if (TextUtils.isEmpty(taoResponse.getData().getPhone())) {
                ARouter.getInstance().build(ArouterConfig.AC_BIND_PHONE).navigation(TBLoginActivity.this);
            }
            // 调用 Handler 来异步设置别名
            mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, taoResponse.getData().getId()));
        }
    }

    @Override
    public AppCompatActivity getActivity() {
        return this;
    }

    private static final int MSG_SET_ALIAS = 1001;
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:
                    LogUtils.debugInfo(TAG, "Set alias in handler.");
                    // 调用 JPush 接口来设置别名。
                    JPushInterface.setAliasAndTags(getApplicationContext(),
                            (String) msg.obj,
                            null,
                            mAliasCallback);
                    break;
                default:
                    LogUtils.debugInfo(TAG, "Unhandled msg - " + msg.what);
            }
        }
    };
    private final TagAliasCallback mAliasCallback = (code, alias, tags) -> {
        String logs;
        switch (code) {
            case 0:
                logs = "Set tag and alias success";
                LogUtils.debugInfo(TAG, logs);
                // 建议这里往 SharePreference 里写一个成功设置的状态。成功设置一次后，以后不必再次设置了。
                break;
            case 6002:
                logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                LogUtils.debugInfo(TAG, logs);
                // 延迟 60 秒来调用 Handler 设置别名
                mHandler.sendMessageDelayed(mHandler.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                break;
            default:
                logs = "Failed with errorCode = " + code;
                LogUtils.debugInfo(TAG, logs);
        }
    };
}
