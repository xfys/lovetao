package com.inner.lovetao.channel.presenter;

import android.app.Application;

import com.inner.lovetao.channel.contract.ShelvesContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


/**
 * Description:
 * <p>
 * Created by feihaokui on 01/14/2019 17:37
 */
@ActivityScope
public class ShelvesPresenter extends BasePresenter<ShelvesContract.Model, ShelvesContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public ShelvesPresenter(ShelvesContract.Model model, ShelvesContract.View rootView) {
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
}
