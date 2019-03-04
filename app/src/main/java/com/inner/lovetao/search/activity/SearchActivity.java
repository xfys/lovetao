package com.inner.lovetao.search.activity;

/*
 *
 *
 * 作 者 :YangFan
 *
 * 版 本 :1.0
 *
 * 创建日期 :2019/1/14      16:25
 *
 * 描 述 :搜索
 *
 * 修订日期 :
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.inner.lovetao.R;
import com.inner.lovetao.search.bean.SearchHistoryItemBean;
import com.inner.lovetao.search.bean.SearchHotItemBean;
import com.inner.lovetao.search.adapter.SearchHistoryAdapter;
import com.inner.lovetao.search.adapter.SearchHotAdapter;
import com.inner.lovetao.search.di.component.DaggerSearchComponent;
import com.inner.lovetao.search.mvp.contract.SearchContract;
import com.inner.lovetao.search.mvp.presenter.SearchPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.Model, SearchContract.View {
    @BindView(R.id.ac_search_edt)
    EditText mEdit;
    @BindView(R.id.ac_search_rcy_history)
    RecyclerView mRcyHistory;
    @BindView(R.id.ac_search_rcy_hot)
    RecyclerView mRcyHot;
    private SearchHistoryAdapter mHistoryAdapter;
    private SearchHotAdapter mHotAdapter;


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerSearchComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.ac_search;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mEdit.clearFocus();
        FlexboxLayoutManager historyLayoutManager = new FlexboxLayoutManager(this);
        historyLayoutManager.setFlexDirection(FlexDirection.ROW);
        historyLayoutManager.setFlexWrap(FlexWrap.WRAP);
        historyLayoutManager.setJustifyContent(JustifyContent.FLEX_START);
        FlexboxLayoutManager hotLayoutManager = new FlexboxLayoutManager(this);
        hotLayoutManager.setFlexDirection(FlexDirection.ROW);
        hotLayoutManager.setFlexWrap(FlexWrap.WRAP);
        hotLayoutManager.setJustifyContent(JustifyContent.FLEX_START);
        mRcyHistory.setLayoutManager(historyLayoutManager);
        mRcyHot.setLayoutManager(hotLayoutManager);
        mHistoryAdapter = new SearchHistoryAdapter(this);
        mRcyHistory.setAdapter(mHistoryAdapter);
        mHotAdapter = new SearchHotAdapter(this);
        mRcyHot.setAdapter(mHotAdapter);
        mPresenter.getHistoryData();
        mPresenter.getHotData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @OnClick({R.id.ac_search_tv_search, R.id.ac_search_iv_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ac_search_tv_search:// 搜索
                break;
            case R.id.ac_search_iv_delete:
                mHistoryAdapter.cleanData();
                break;
            default:
                break;
        }
    }

    @Override
    public void RefreshHistoryData(List<SearchHistoryItemBean> list) {
        mHistoryAdapter.setData(list);
    }

    @Override
    public void RefreshHotData(List<SearchHotItemBean> list) {
        mHotAdapter.setData(list);
    }
}
