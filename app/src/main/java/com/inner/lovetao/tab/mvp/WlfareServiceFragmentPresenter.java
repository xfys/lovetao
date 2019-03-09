package com.inner.lovetao.tab.mvp;

import android.app.Application;

import com.inner.lovetao.tab.contract.WlfareServiceFragmentContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * desc:
 * Created by xcz
 * on 2019/1/22.
 */
@ActivityScope
public class WlfareServiceFragmentPresenter extends BasePresenter<WlfareServiceFragmentContract.Model, WlfareServiceFragmentContract.View> {

    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public WlfareServiceFragmentPresenter(WlfareServiceFragmentContract.Model model, WlfareServiceFragmentContract.View rootView) {
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
