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

import com.inner.lovetao.beans.response.search.SearchHistoryItemBean;
import com.inner.lovetao.beans.response.search.SearchHotItemBean;
import com.inner.lovetao.search.mvp.contract.SearchContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;

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
        List<SearchHistoryItemBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SearchHistoryItemBean bean = new SearchHistoryItemBean();
            bean.setDesc("历史");
            list.add(bean);
        }
        mRootView.RefreshHistoryData(list);
    }

    public void getHotData() {
        List<SearchHotItemBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            SearchHotItemBean bean = new SearchHotItemBean();
            bean.setDesc("热搜");
            list.add(bean);
        }
        mRootView.RefreshHotData(list);
    }
}
