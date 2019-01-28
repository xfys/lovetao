package com.inner.lovetao.loginregister.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.inner.lovetao.R;
import com.inner.lovetao.loginregister.TimeCount;
import com.inner.lovetao.loginregister.di.component.DaggerBindPhoneActivityComponent;
import com.inner.lovetao.loginregister.mvp.contract.BindPhoneActivityContract;
import com.inner.lovetao.loginregister.mvp.presenter.BindPhoneActivityPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * desc:绑定手机号
 * Created by xcz
 * on 2019/01/28
 */
public class BindPhoneActivityActivity extends BaseActivity<BindPhoneActivityPresenter> implements BindPhoneActivityContract.View, TimeCount.CountDownTimerListener {
    /**
     * 输入的手机号
     */
    @BindView(R.id.edit_phone_number)
    EditText phoneNumber;
    /**
     * 验证码
     */
    @BindView(R.id.edit_verfiy_code)
    EditText verfiyCode;
    /**
     * 邀请码
     */
    @BindView(R.id.edit_invest_code)
    EditText investCode;
    /**
     * 确定
     */
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    /**
     * 获取验证码
     */
    @BindView(R.id.tv_get_verfiy_code)
    TextView tvGetCode;
    private TimeCount timeCount;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerBindPhoneActivityComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_bind_phone; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if (getLine() != null) {
            getLine().setVisibility(View.GONE);
        }
        timeCount = new TimeCount(60000, 1000);
        initListner();
    }

    @Override
    protected int getMenuId() {
        return R.menu.menu_skip;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_skip:
                killMyself();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initListner() {
        timeCount.setCountDownTimerListener(this);
        phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        verfiyCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @OnClick({R.id.tv_get_verfiy_code, R.id.tv_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            //获取验证码
            case R.id.tv_get_verfiy_code:
                if (timeCount != null) {
                    timeCount.start();
                }
                break;
            //确定
            case R.id.tv_confirm:
                break;
        }
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

    @Override
    public void init() {
        tvGetCode.setText(getString(R.string.bind_phone_get_verfiy_code_desc));
        tvGetCode.setEnabled(true);
    }

    @Override
    public void timeover() {
        if (tvGetCode != null) {
            tvGetCode.setText(getString(R.string.bind_phone_verfiy_code_again));
            tvGetCode.setEnabled(true);
        }
    }

    @Override
    public void update(long millisUntilFinished) {
        tvGetCode.setEnabled(false);
        long time = (millisUntilFinished / 1000);
        if (time < 10) {
            tvGetCode.setText("0");
            tvGetCode.append(String.valueOf(time));
            tvGetCode.append("s后重发");
        } else {
            tvGetCode.setText(String.valueOf(time));
            tvGetCode.append("s后重发");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timeCount.onFinish();
        timeCount.setCountDownTimerListener(null);
        timeCount = null;
    }
}
