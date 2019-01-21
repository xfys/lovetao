package com.inner.lovetao.search.mvp;

/*
 *
 *
 * 作 者 :YangFan
 *
 * 版 本 :1.0
 *
 * 创建日期 :2019/1/14      16:26
 *
 * 描 述 :搜索结果Presenter
 *
 * 修订日期 :
 */

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.inner.lovetao.beans.response.search.SearchHistoryItemBean;
import com.inner.lovetao.beans.response.search.SearchHotItemBean;
import com.jess.arms.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPresenter extends BasePresenter<SearchContract.Model, SearchContract.View> {

    public SearchResultPresenter(SearchContract.Model model, SearchContract.View view) {
        super(model, view);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {

    }
}
