package com.inner.lovetao.tab.mvp;

import android.app.Application;

import com.inner.lovetao.config.ConfigInfo;
import com.inner.lovetao.core.TaoResponse;
import com.inner.lovetao.tab.bean.ProductItemBean;
import com.inner.lovetao.tab.contract.CategoryFragmentContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

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

    /**
     * 获取精品数据
     */
    public void getProductList(int pageNum, int categoryId) {
        mModel.getProductData(pageNum, ConfigInfo.PAGE_SIZE, categoryId)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(1, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用Rxlifecycle,使Disposable和Activity一起销毁
                .subscribe(new ErrorHandleSubscriber<TaoResponse<List<ProductItemBean>>>(mErrorHandler) {
                    @Override
                    public void onNext(TaoResponse<List<ProductItemBean>> response) {
                        if (response.isSuccess()) {
                            mRootView.getProductdataSu(response.getData());
                        } else {
                            mRootView.showMessage(response.getMessage());
                        }
                    }
                });
    }

    public ImageLoader getmImageLoader() {
        return mImageLoader;
    }
}
