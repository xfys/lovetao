package com.inner.lovetao.loginregister.mvp.presenter;

import android.app.Application;
import android.text.TextUtils;

import com.inner.lovetao.config.UserInfo;
import com.inner.lovetao.core.TaoResponse;
import com.inner.lovetao.loginregister.mvp.contract.BindPhoneActivityContract;
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
public class BindPhoneActivityPresenter extends BasePresenter<BindPhoneActivityContract.Model, BindPhoneActivityContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public BindPhoneActivityPresenter(BindPhoneActivityContract.Model model, BindPhoneActivityContract.View rootView) {
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
     * 获取手机验证码
     */
    public void getUserCode(String phone) {
        if (TextUtils.isEmpty(phone)) return;
        mModel.getPhoneCode(phone)
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
                .subscribe(new ErrorHandleSubscriber<TaoResponse>(mErrorHandler) {
                    @Override
                    public void onNext(TaoResponse response) {
                        if (response.isSuccess()) {
                            mRootView.getPhoneCodeSu();
                        } else {
                            mRootView.showMessage(response.getMessage());
                        }
                    }
                });
    }

    /**
     * 绑定用户
     */
    public void bindUserPhone(String phone, String verifyCode, String InvitationCode, String nike, String imgUrl, String openId, String sid) {
        mModel.bindPhone(phone, verifyCode, InvitationCode, nike, imgUrl, openId, sid)
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
                .subscribe(new ErrorHandleSubscriber<TaoResponse<UserInfo>>(mErrorHandler) {
                    @Override
                    public void onNext(TaoResponse<UserInfo> response) {
                        if (response.isSuccess()) {
                            mRootView.bindPhoneNumSu(response.getData());
                        } else {
                            mRootView.showMessage(response.getMessage());
                        }
                    }
                });
    }
}
