package com.inner.lovetao.webview.mvp.contract;

import android.webkit.JsPromptResult;
import android.webkit.JsResult;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;


/**
 * Desc:
 * Created by xcz
 * on 2019/03/06
 */
public interface WebContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

        void showProgress(int newProgress);

        void changeTitle(String title);

        void showJsAlert(String message, JsResult result);

        void onJsConfirm(String message, JsResult result);

        void onJsPrompt(String message, String defaultValue, JsPromptResult result);

        void downLoad(String url);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {

    }
}
