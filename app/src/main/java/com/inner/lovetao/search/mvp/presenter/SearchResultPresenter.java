package com.inner.lovetao.search.mvp.presenter;

import android.app.Application;

import com.inner.lovetao.config.ConfigInfo;
import com.inner.lovetao.core.TaoResponse;
import com.inner.lovetao.search.mvp.contract.SearchResultContract;
import com.inner.lovetao.tab.bean.ProductItemBean;
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
 * desc:
 * Created by xcz
 */
@ActivityScope
public class SearchResultPresenter extends BasePresenter<SearchResultContract.Model, SearchResultContract.View> {
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    @Inject
    ImageLoader imageLoader;

    @Inject
    public SearchResultPresenter(SearchResultContract.Model model, SearchResultContract.View rootView) {
        super(model, rootView);
    }

    /**
     * 获取推荐类型
     * @param pageNum
     * @param title
     */
    public void getSearchData(int pageNum, String title){
        mModel.getSearchData(pageNum, ConfigInfo.PAGE_SIZE,title)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> mRootView.showLoading())
                .retryWhen(new RetryWithDelay(1, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(()->mRootView.hideLoading())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用Rxlifecycle,使Disposable和Activity一起销毁
                .subscribe(new ErrorHandleSubscriber<TaoResponse<List<ProductItemBean>>>(mErrorHandler) {
                    @Override
                    public void onNext(TaoResponse<List<ProductItemBean>> listTaoResponse) {
                        if(listTaoResponse.isSuccess()){
                            mRootView.getSearchResultData(listTaoResponse.getData());
                        }else {
                            mRootView.showMessage(listTaoResponse.getMessage());
                        }
                    }
                });
    }

    /**
     * 获取销量和最新
     * @param pageNum
     * @param title
     * @param sortName
     */
    public void getSearchDataForSort(int pageNum, String title,String sortName){
        mModel.getSearchSortData(pageNum, ConfigInfo.PAGE_SIZE,title,sortName)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> mRootView.showLoading())
                .retryWhen(new RetryWithDelay(1, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(()->mRootView.hideLoading())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用Rxlifecycle,使Disposable和Activity一起销毁
                .subscribe(new ErrorHandleSubscriber<TaoResponse<List<ProductItemBean>>>(mErrorHandler) {
                    @Override
                    public void onNext(TaoResponse<List<ProductItemBean>> listTaoResponse) {
                        if(listTaoResponse.isSuccess()){
                            mRootView.getSearchResultData(listTaoResponse.getData());
                        }else {
                            mRootView.showMessage(listTaoResponse.getMessage());
                        }
                    }
                });
    }

    /**
     * 获取价格排序（升序和降序）
     * @param pageNum
     * @param title
     * @param sortName
     * @param sortOrder
     */
    public void getSearchDataForSorts(int pageNum, String title,String sortName,String sortOrder){
        mModel.getSearchSortsData(pageNum, ConfigInfo.PAGE_SIZE,title,sortName,sortOrder)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> mRootView.showLoading())
                .retryWhen(new RetryWithDelay(1, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(()->mRootView.hideLoading())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用Rxlifecycle,使Disposable和Activity一起销毁
                .subscribe(new ErrorHandleSubscriber<TaoResponse<List<ProductItemBean>>>(mErrorHandler) {
                    @Override
                    public void onNext(TaoResponse<List<ProductItemBean>> listTaoResponse) {
                        if(listTaoResponse.isSuccess()){
                            mRootView.getSearchResultData(listTaoResponse.getData());
                        }else {
                            mRootView.showMessage(listTaoResponse.getMessage());
                        }
                    }
                });
    }

    public ImageLoader getImageLoader(){
        return imageLoader;
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
