package com.inner.lovetao.search.mvp.presenter;

/*
 *
 *
 * 作 者 :YangFan
 *
 * 版 本 :1.0
 *
 * 创建日期 :2019/1/14      16:26
 *
 * 描 述 :搜索Presenter
 *
 * 修订日期 :
 */

import android.app.Application;

import com.inner.lovetao.config.ConfigInfo;
import com.inner.lovetao.search.bean.SearchHistoryItemBean;
import com.inner.lovetao.search.bean.SearchHotItemBean;
import com.inner.lovetao.search.mvp.contract.SearchContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.DataHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

@ActivityScope
public class SearchPresenter extends BasePresenter<SearchContract.Model, SearchContract.View> {

    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;

    @Inject
    public SearchPresenter(SearchContract.Model model, SearchContract.View rootView) {
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

    public void getHistoryData() {
        List<SearchHistoryItemBean> list = DataHelper.getDeviceData(mApplication.getBaseContext(), ConfigInfo.HISTORY_EDIT);
        mRootView.RefreshHistoryData(list);
    }

    public void getHotData() {
        List<SearchHotItemBean> list = new ArrayList<>();
        list.add(new SearchHotItemBean("防护喷雾9.9元"));
        list.add(new SearchHotItemBean("内裤 女"));
        list.add(new SearchHotItemBean("蓝牙耳机"));
        list.add(new SearchHotItemBean("面膜"));
        list.add(new SearchHotItemBean("零食"));
        list.add(new SearchHotItemBean("小白鞋"));
        list.add(new SearchHotItemBean("行李箱 女"));
        list.add(new SearchHotItemBean("俏美人"));
        list.add(new SearchHotItemBean("小米手机9"));
        list.add(new SearchHotItemBean("上衣"));
        mRootView.RefreshHotData(list);
    }
}
