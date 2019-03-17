package com.inner.lovetao.settings.mvp.presenter;

import android.app.Application;

import com.inner.lovetao.config.UserInstance;
import com.inner.lovetao.core.TaoResponse;
import com.inner.lovetao.settings.mvp.contract.SuggestContract;
import com.inner.lovetao.settings.request_bean.SuggestRequest;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;


/**
 * Desc:
 * Created by xcz
 * on 2019/02/14
 */
@ActivityScope
public class SuggestPresenter extends BasePresenter<SuggestContract.Model, SuggestContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public SuggestPresenter(SuggestContract.Model model, SuggestContract.View rootView) {
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

    /**
     * 提交建议
     */
    public void suggestCommit(SuggestRequest content) {

        mModel.suggest(content)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(1, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .doOnSubscribe(disposable -> {
                    //显示loading
                    mRootView.showLoading();
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    //隐藏对话框
                    mRootView.hideLoading();
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用Rxlifecycle,使Disposable和Activity一起销毁
                .subscribe(new ErrorHandleSubscriber<TaoResponse<Boolean>>(mErrorHandler) {
                    @Override
                    public void onNext(TaoResponse<Boolean> response) {
                        if (response.isSuccess()) {
                            mRootView.suggestSu();
                        } else {
                            mRootView.showMessage(response.getMessage());
                        }
                    }
                });
    }
}
