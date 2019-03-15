package com.inner.lovetao.settings.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.inner.lovetao.R;
import com.inner.lovetao.config.ArouterConfig;
import com.inner.lovetao.settings.di.component.DaggerSuggestComponent;
import com.inner.lovetao.settings.mvp.contract.SuggestContract;
import com.inner.lovetao.settings.mvp.presenter.SuggestPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * Desc:意见反馈
 * Created by xcz
 * on 2019/02/14
 */
@Route(path = ArouterConfig.AC_SUGGEST)
public class SuggestActivity extends BaseActivity<SuggestPresenter> implements SuggestContract.View {
    /**
     * 建议内容
     */
    @BindView(R.id.edit_suggest)
    EditText editSuggest;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerSuggestComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_suggest; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @OnClick({R.id.tv_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm:
                if (TextUtils.isEmpty(editSuggest.getText().toString())) {
                    showMessage(getString(R.string.suggest_toast));
                } else {
                    mPresenter.suggestCommit(editSuggest.getText().toString().trim());
                }
                break;
        }

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
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
    public void suggestSu() {
        showMessage(getString(R.string.ac_suggest_toast));
        killMyself();
    }
}
