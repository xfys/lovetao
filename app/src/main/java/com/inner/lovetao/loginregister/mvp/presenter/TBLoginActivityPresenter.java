package com.inner.lovetao.loginregister.mvp.presenter;

import android.app.Application;

import com.inner.lovetao.core.TaoResponse;
import com.inner.lovetao.loginregister.mvp.contract.TBLoginActivityContract;
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
 * desc:
 * Created by xcz
 * on 2019/01/28
 */
@ActivityScope
public class TBLoginActivityPresenter extends BasePresenter<TBLoginActivityContract.Model, TBLoginActivityContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public TBLoginActivityPresenter(TBLoginActivityContract.Model model, TBLoginActivityContract.View rootView) {
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

    public void getTestData(int type) {
        mModel.sendTbInfo(type)
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
                .subscribe(new ErrorHandleSubscriber<TaoResponse<String>>(mErrorHandler) {
                    @Override
                    public void onNext(TaoResponse<String> stringTaoResponse) {
                        mRootView.getDatasu(stringTaoResponse);
                    }
                });
    }
}
