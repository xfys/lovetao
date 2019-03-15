package com.inner.lovetao.settings.mvp.presenter;

import android.app.Application;

import com.inner.lovetao.settings.mvp.contract.SettingActivityContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


/**
 * desc:
 * Created by xcz
 * on 2019/01/24
 */
@ActivityScope
public class SettingActivityPresenter extends BasePresenter<SettingActivityContract.Model, SettingActivityContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public SettingActivityPresenter(SettingActivityContract.Model model, SettingActivityContract.View rootView) {
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

    public ImageLoader getmImageLoader() {
        return mImageLoader;
    }

}
