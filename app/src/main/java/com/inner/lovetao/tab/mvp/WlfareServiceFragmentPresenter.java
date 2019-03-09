package com.inner.lovetao.tab.mvp;

import android.app.Application;

import com.inner.lovetao.core.TaoResponse;
import com.inner.lovetao.tab.bean.BannerBean;
import com.inner.lovetao.tab.contract.WlfareServiceFragmentContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

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

    public void getBannerList(int type){
        mModel.getBannerData(type)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(1,2))
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用Rxlifecycle,使Disposable和Activity一起销毁
                .subscribe(new ErrorHandleSubscriber<TaoResponse<List<BannerBean>>>(mErrorHandler) {
                    @Override
                    public void onNext(TaoResponse<List<BannerBean>> listTaoResponse) {
                        if(listTaoResponse.isSuccess()){
                            mRootView.getBannerDataSu(type,listTaoResponse.getData());
                        }else {
                            mRootView.showMessage(listTaoResponse.getMessage());
                        }
                    }
                });
    }

    public ImageLoader getmImageLoader(){
        return mImageLoader;
    }

}
