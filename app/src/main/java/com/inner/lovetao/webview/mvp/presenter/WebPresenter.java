package com.inner.lovetao.webview.mvp.presenter;

import android.app.Application;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.Gson;
import com.inner.lovetao.config.UserInstance;
import com.inner.lovetao.webview.js_bean.ShareBean;
import com.inner.lovetao.webview.mvp.contract.WebContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.LogUtils;

import java.net.URLDecoder;

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
    Gson mGson;
    private WebView webview;

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
        this.mGson = null;
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
        this.webview = webView;
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

        private String urlStr;

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //假定传入进来的 url = lovetao://webview?action=1&data={"shareUrl":"","title":"题目","content":"内容","shareImg":""}
            LogUtils.debugInfo(url);
            try {
                url = url.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
                urlStr = URLDecoder.decode(url, "UTF-8");
            } catch (Exception e) {

            }
            LogUtils.debugInfo(urlStr);
            Uri uri = Uri.parse(urlStr);
            // 如果url的协议 = 预先约定的 js 协议
            // 就解析往下解析参数
            if (uri.getScheme().equals("lovetao")) {
                // 如果 authority  = 预先约定协议里的 webview，即代表都符合约定的协议
                // 所以拦截url,下面JS开始调用Android需要的方法
                if (uri.getAuthority().equals("webview")) {
                    //  步骤3：
                    // 执行JS所需要调用的逻辑
                    // 可以在协议上带有参数并传递到Android上
                    String action = uri.getQueryParameter("action");
                    switch (Integer.valueOf(action)) {
                        case 1:
                            if (mGson != null) {
                                String data;
                                try {
                                    data = urlStr.split("data=")[1];
                                    if (data.startsWith("{") && data.endsWith("}")) {
                                        ShareBean shareBean = mGson.fromJson(data, ShareBean.class);
                                        mRootView.share(shareBean);
                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            break;
                        //跳转到支付宝
                        case 2:
                            if (mGson != null) {
                                String data = uri.getQueryParameter("data");
                                if (!TextUtils.isEmpty(data)) {
                                    mRootView.openAliPay(data);

                                }
                            }
                            break;
                    }

                }
                return true;
            } else if (uri.getScheme().equals("tbopen")) {
                return false;
            } else if (uri.getScheme().equals("http") || uri.getScheme().equals("https")) {
                view.loadUrl(url);
                return true;
            } else {
                return super.shouldOverrideUrlLoading(view, url);
            }

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
            if (webview != null) {
                loginSuccess(webview);
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

    public void loginSuccess(WebView webView) {
        if (UserInstance.getInstance().isLogin(webView.getContext())) {
            String id = UserInstance.getInstance().getUserInfo(webView.getContext()).getId();
            String invitationCode = UserInstance.getInstance().getUserInfo(webView.getContext()).getInvitationCode();
            String shareCode = UserInstance.getInstance().getUserInfo(webView.getContext()).getShareCode();
//            webView.loadUrl("javascript:loginSuccess(\"" + id + "\",\"" + invitationCode + "\")");
            webView.loadUrl("javascript:loginSuccess(\"" + id + "\",\"" + invitationCode + "\",\"" + shareCode + "\")");
        }
    }
}
