package com.inner.lovetao.mineassets.mvp.presenter;

import android.app.Application;

import com.inner.lovetao.mineassets.mvp.contract.EarningsContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;


/**
 * Description:
 * <p>
 * Created by feihaokui on 02/15/2019 11:18
 */
@ActivityScope
public class EarningsPresenter extends BasePresenter<EarningsContract.Model, EarningsContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public EarningsPresenter(EarningsContract.Model model, EarningsContract.View rootView) {
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
