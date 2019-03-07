package com.inner.lovetao.webview.mvp.presenter;

import android.app.Application;
import android.net.http.SslError;
import android.os.Build;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.inner.lovetao.webview.mvp.contract.WebContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.LogUtils;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


/**
 * Desc:
 * Created by xcz
 * on 2019/03/06
 */
@ActivityScope
public class WebPresenter extends BasePresenter<WebContract.Model, WebContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public WebPresenter(WebContract.Model model, WebContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

    public void setWebviewSettings(WebSettings webSettings) {
        //设置js可以直接打开窗口，如window.open()，默认为false
        webSettings.setJavaScriptEnabled(true);
        // 是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 是否可以缩放，默认true
        webSettings.setSupportZoom(false);
        // 是否显示缩放按钮，默认false
        webSettings.setBuiltInZoomControls(true);
        // 设置此属性，可任意比例缩放。大视图模式
        webSettings.setUseWideViewPort(false);
        // 和setUseWideViewPort(true)一起解决网页自适应问题
        webSettings.setLoadWithOverviewMode(true);
        // 是否使用缓存
        webSettings.setAppCacheEnabled(true);
        // DOM Storage
        webSettings.setDomStorageEnabled(true);
    }

    public void setWebClient(WebView webView) {
        //设置WebViewClient
        webView.setWebViewClient(mWebViewClient);
        // 创建WebViewChromeClient
        webView.setWebChromeClient(mWebChromeClient);
//        webView.addJavascriptInterface(new WDScriptInterface(), "FFTest");
        webView.setDownloadListener((url, userAgent, contentDisposition, mimetype, contentLength) -> {
            if (mRootView != null) {
                mRootView.downLoad(url);
            }
        });
    }

    private final WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("lovetao://")) {
                //TODO:自定义事件
            } else if (url.startsWith("tbopen://")) {
                return false;
            } else {
                view.loadUrl(url);
            }
            return true;
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            if (mRootView != null) {
                mRootView.showMessage("网络链接失败");
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                LogUtils.warnInfo(error.getDescription().toString());
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (mRootView != null) {
                mRootView.showProgress(100);
                mRootView.changeTitle(view.getTitle());
            }
        }
    };
    private final WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (mRootView != null) {
                mRootView.changeTitle(title);
            }
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (mRootView != null) {
                mRootView.showProgress(newProgress);
            }
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            if (mRootView != null) {
                mRootView.showJsAlert(message, result);
            }
            return true;
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
            if (mRootView != null) {
                mRootView.onJsConfirm(message, result);
            }
            return true;
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            if (mRootView != null) {
                mRootView.onJsPrompt(message, defaultValue, result);
            }
            return true;
        }
    };
}
