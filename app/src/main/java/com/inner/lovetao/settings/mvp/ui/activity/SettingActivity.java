package com.inner.lovetao.settings.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.inner.lovetao.R;
import com.inner.lovetao.config.ArouterConfig;
import com.inner.lovetao.config.UserInfo;
import com.inner.lovetao.config.UserInstance;
import com.inner.lovetao.home.activity.MainActivity;
import com.inner.lovetao.settings.di.component.DaggerSettingActivityComponent;
import com.inner.lovetao.settings.mvp.contract.SettingActivityContract;
import com.inner.lovetao.settings.mvp.presenter.SettingActivityPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.config.CommonImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DataHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * desc:设置页面
 * Created by xcz
 * on 2019/01/24
 */
@Route(path = ArouterConfig.AC_SETTING)
public class SettingActivity extends BaseActivity<SettingActivityPresenter> implements SettingActivityContract.View {
    /**
     * 头像
     */
    @BindView(R.id.iv_head)
    ImageView ivHead;
    /**
     * 昵称
     */
    @BindView(R.id.tv_nike_name)
    TextView nikeName;
    /**
     * 手机号
     */
    @BindView(R.id.tv_bind_phone_number)
    TextView bindPhoneNumber;
    /**
     * 支付宝账号
     */
    @BindView(R.id.tv_bind_alipay)
    TextView bindAlipay;
    /**
     * 缓存大小
     */
    @BindView(R.id.tv_data_sum)
    TextView dataSum;
    private UserInfo userInfo;

    @Override

    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerSettingActivityComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_setting; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if (getLine() != null) {
            getLine().setVisibility(View.GONE);
        }
        dataSum.setText(DataHelper.bytes2kb(DataHelper.getDirSize(DataHelper.getCacheFile(this))));
    }


    @OnClick({R.id.rl_head, R.id.rl_nike_name, R.id.rl_bind_phone, R.id.rl_bind_alibaba, R.id.rl_data_sum, R.id.tv_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_head:
                break;
            case R.id.rl_nike_name:
                break;
            case R.id.rl_bind_phone:
                if (userInfo != null && TextUtils.isEmpty(userInfo.getPhone())) {
                    ARouter.getInstance().build(ArouterConfig.AC_BIND_PHONE).navigation(this);
                }
                break;
            case R.id.rl_bind_alibaba:
                break;
            case R.id.rl_data_sum:
                DataHelper.cleanExternalCache(this);
                dataSum.setText(DataHelper.bytes2kb(DataHelper.getDirSize(DataHelper.getCacheFile(this))));
                ArmsUtils.makeText(this, ArmsUtils.getString(this, R.string.settings_clear_su));
                break;
            case R.id.tv_exit:
                UserInstance.getInstance().clearUserInfo(this);
                startActivity(new Intent(this, MainActivity.class));
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
    protected void onStart() {
        super.onStart();
        initUser();
    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }


    @Override
    public void killMyself() {
        finish();
    }

    private void initUser() {
        userInfo = UserInstance.getInstance().getUserInfo(this);
        if (userInfo != null) {
            if (TextUtils.isEmpty(userInfo.getNick())) {
                nikeName.setText("未设置");
            } else {
                nikeName.setText(userInfo.getNick());
            }
            if (TextUtils.isEmpty(userInfo.getPhone())) {
                bindPhoneNumber.setText("未绑定手机号");
            } else {
                bindPhoneNumber.setText(userInfo.getPhone());
            }
            if (TextUtils.isEmpty(userInfo.getHeadPicUrl())) {
                ivHead.setImageResource(R.mipmap.icon_mine_photo);
            } else {
                mPresenter.getmImageLoader().loadImage(this,
                        CommonImageConfigImpl
                                .builder()
                                .isCropCenter(true)
                                .isCropCircle(true)
                                .url(userInfo.getHeadPicUrl())
                                .imageView(ivHead)
                                .build());
            }
        }
    }
}
