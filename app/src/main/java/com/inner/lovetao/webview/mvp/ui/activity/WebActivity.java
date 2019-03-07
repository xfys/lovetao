package com.inner.lovetao.webview.mvp.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.inner.lovetao.R;
import com.inner.lovetao.config.ArouterConfig;
import com.inner.lovetao.webview.di.component.DaggerWebComponent;
import com.inner.lovetao.webview.mvp.contract.WebContract;
import com.inner.lovetao.webview.mvp.presenter.WebPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * Desc:
 * Created by xcz
 * on 2019/03/06
 */
@Route(path = ArouterConfig.AC_WEBVIEW)
public class WebActivity extends BaseActivity<WebPresenter> implements WebContract.View {

    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.webview_progress)
    ProgressBar webProgress;

    @Autowired(name = ArouterConfig.ParamKey.STR_WEBVIEW_URL)
    String url;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerWebComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_web; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if (webView != null) {
            mPresenter.setWebviewSettings(webView.getSettings());
            webView.requestFocus();//使页面获得焦点
            mPresenter.setWebClient(webView);
            if (!TextUtils.isEmpty(url)) {
                webView.loadUrl(url);
            }
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
    public void showProgress(int newProgress) {
        webProgress.setVisibility(View.VISIBLE);
        webProgress.setProgress(newProgress * 100);
        if (newProgress == 100) webProgress.setVisibility(View.GONE);
    }

    @Override
    public void changeTitle(String title) {
        if (!TextUtils.isEmpty(title) && null != mToolbarTitle) {
            mToolbarTitle.setText(title);
        }
    }

    @Override
    public void showJsAlert(String message, JsResult result) {
        // 构建一个Builder来显示网页中的alert对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.common_alert));
        if (!TextUtils.isEmpty(message)) {
            builder.setTitle(message);
        }
        builder.setPositiveButton(getString(R.string.common_confirm), (dialog, which) -> result.confirm());
        builder.setCancelable(false);
        builder.create();
        builder.show();
    }

    @Override
    public void onJsConfirm(String message, JsResult result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.common_alert));
        if (!TextUtils.isEmpty(message)) {
            builder.setTitle(message);
        }
        builder.setPositiveButton(getString(R.string.common_confirm), (dialog, which) -> result.confirm());
        builder.setNegativeButton(getString(R.string.common_cancel), (dialog, which) -> result.cancel());
        builder.setCancelable(false);
        builder.create();
        builder.show();
    }

    @Override
    public void onJsPrompt(String message, String defaultValue, JsPromptResult result) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (!TextUtils.isEmpty(message)) {
            builder.setTitle(message);
        }
        final EditText et = new EditText(this);
        et.setSingleLine();
        et.setText(defaultValue);
        builder.setView(et);
        builder.setPositiveButton(getString(R.string.common_confirm), (dialog, which) -> result.confirm(et.getText().toString()))
                .setNeutralButton(getString(R.string.common_cancel), (dialog, which) -> result.cancel());
        builder.setCancelable(false);
        builder.create();
        builder.show();
    }

    @Override
    public void downLoad(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
            return;
        } else {
            super.onBackPressed();
        }
    }
}
