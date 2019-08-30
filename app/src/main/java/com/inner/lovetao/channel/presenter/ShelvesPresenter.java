package com.inner.lovetao.channel.presenter;

import android.app.Application;
import android.text.TextUtils;

import com.inner.lovetao.channel.contract.ShelvesContract;
import com.inner.lovetao.config.ConfigInfo;
import com.inner.lovetao.config.RefreshConfig;
import com.inner.lovetao.core.TaoResponse;
import com.inner.lovetao.tab.bean.ProductItemBean;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;


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

    /**
     * 获取今日上新
     *
     * @param pageNum
     */
    public void getTodayData(int pageNum, String sortName, String sortOrder) {
        Observable<TaoResponse<List<ProductItemBean>>> todaySale = null;
        if (TextUtils.equals(RefreshConfig.SORT_RECOMMEND, sortName)) {
            todaySale = mModel.getTodaySale(pageNum, ConfigInfo.PAGE_SIZE);
        } else if (TextUtils.equals(RefreshConfig.SORT_NEWEST, sortName) || TextUtils.equals(RefreshConfig.SORT_SALES, sortName)) {
            todaySale = mModel.getTodaySale(pageNum, ConfigInfo.PAGE_SIZE, sortName);
        } else if (TextUtils.equals(RefreshConfig.SORT_PRICE, sortName)) {
            todaySale = mModel.getTodaySale(pageNum, ConfigInfo.PAGE_SIZE, sortName, sortOrder);
        }
        todaySale
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
                .subscribe(new ErrorHandleSubscriber<TaoResponse<List<ProductItemBean>>>(mErrorHandler) {
                    @Override
                    public void onNext(TaoResponse<List<ProductItemBean>> response) {
                        if (response.isSuccess()) {
                            mRootView.getProductListSu(response.getData());
                        } else {
                            mRootView.showMessage(response.getMessage());
                        }
                    }
                });
    }

    /**
     * 获取99特卖
     *
     * @param pageNum
     */
    public void getSale99(int pageNum, String sortName, String sortOrder) {
        Observable<TaoResponse<List<ProductItemBean>>> sale_99 = null;
        if (TextUtils.equals(RefreshConfig.SORT_RECOMMEND, sortName)) {
            sale_99 = mModel.getSale_99(pageNum, ConfigInfo.PAGE_SIZE);
        } else if (TextUtils.equals(RefreshConfig.SORT_NEWEST, sortName) || TextUtils.equals(RefreshConfig.SORT_SALES, sortName)) {
            sale_99 = mModel.getSale_99(pageNum, ConfigInfo.PAGE_SIZE, sortName);
        } else if (TextUtils.equals(RefreshConfig.SORT_PRICE, sortName)) {
            sale_99 = mModel.getSale_99(pageNum, ConfigInfo.PAGE_SIZE, sortName, sortOrder);
        }
        sale_99
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
                .subscribe(new ErrorHandleSubscriber<TaoResponse<List<ProductItemBean>>>(mErrorHandler) {
                    @Override
                    public void onNext(TaoResponse<List<ProductItemBean>> response) {
                        if (response.isSuccess()) {
                            mRootView.getProductListSu(response.getData());
                        } else {
                            mRootView.showMessage(response.getMessage());
                        }
                    }
                });
    }

    /**
     * 获取大额神拳
     *
     * @param pageNum
     */
    public void getBigCoupon(int pageNum, String sortName, String sortOrder) {
        Observable<TaoResponse<List<ProductItemBean>>> bigSale = null;
        if (TextUtils.equals(RefreshConfig.SORT_RECOMMEND, sortName)) {
            bigSale = mModel.getBigSale(pageNum, ConfigInfo.PAGE_SIZE);
        } else if (TextUtils.equals(RefreshConfig.SORT_NEWEST, sortName) || TextUtils.equals(RefreshConfig.SORT_SALES, sortName)) {
            bigSale = mModel.getBigSale(pageNum, ConfigInfo.PAGE_SIZE, sortName);
        } else if (TextUtils.equals(RefreshConfig.SORT_PRICE, sortName)) {
            bigSale = mModel.getBigSale(pageNum, ConfigInfo.PAGE_SIZE, sortName, sortOrder);
        }

        bigSale.subscribeOn(Schedulers.io())
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
                .subscribe(new ErrorHandleSubscriber<TaoResponse<List<ProductItemBean>>>(mErrorHandler) {
                    @Override
                    public void onNext(TaoResponse<List<ProductItemBean>> response) {
                        if (response.isSuccess()) {
                            mRootView.getProductListSu(response.getData());
                        } else {
                            mRootView.showMessage(response.getMessage());
                        }
                    }
                });
    }

    /**
     * 获取活动数据
     */
    public void getAcData(int pageNum, int activityId, String sortName, String sortOrder) {
        Observable<TaoResponse<List<ProductItemBean>>> acData = null;
        if (TextUtils.equals(RefreshConfig.SORT_RECOMMEND, sortName)) {
            acData = mModel.getAcData(pageNum, ConfigInfo.PAGE_SIZE, activityId);
        } else if (TextUtils.equals(RefreshConfig.SORT_NEWEST, sortName) || TextUtils.equals(RefreshConfig.SORT_SALES, sortName)) {
            acData = mModel.getAcData(pageNum, ConfigInfo.PAGE_SIZE, activityId, sortName);
        } else if (TextUtils.equals(RefreshConfig.SORT_PRICE, sortName)) {
            acData = mModel.getAcData(pageNum, ConfigInfo.PAGE_SIZE, activityId, sortName, sortOrder);
        }
        acData
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
                .subscribe(new ErrorHandleSubscriber<TaoResponse<List<ProductItemBean>>>(mErrorHandler) {
                    @Override
                    public void onNext(TaoResponse<List<ProductItemBean>> response) {
                        if (response.isSuccess()) {
                            mRootView.getProductListSu(response.getData());
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
