package com.inner.lovetao.tab.mvp;

import android.app.Application;

import com.inner.lovetao.tab.contract.CategoryFragmentContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * Description:
 * <p>
 * Created by feihaokui on 2019-01-29.
 */
@ActivityScope
public class CategoryFragmentPresenter extends BasePresenter<CategoryFragmentContract.Model, CategoryFragmentContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public CategoryFragmentPresenter(CategoryFragmentContract.Model model, CategoryFragmentContract.View rootView) {
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
