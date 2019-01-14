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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.inner.lovetao.R;
import com.inner.lovetao.search.adapter.SearchHistoryAdapter;
import com.inner.lovetao.search.adapter.SearchHotAdapter;
import com.inner.lovetao.search.mvp.SearchContract;
import com.inner.lovetao.search.mvp.SearchPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity<SearchPresenter> implements SearchContract.Model, SearchContract.View {
    @BindView(R.id.ac_search_edt)
    EditText mEdit;
    @BindView(R.id.ac_search_tv_cancel)
    TextView mTvCancel;
    @BindView(R.id.ac_search_iv_delete)
    ImageView mIvDelete;
    @BindView(R.id.ac_search_rcy_history)
    RecyclerView mRcyHistory;
    @BindView(R.id.ac_search_rcy_hot)
    RecyclerView mRcyHot;
    private SearchHistoryAdapter mHistoryAdapter;
    private SearchHotAdapter mHotAdapter;


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

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
//        mHistoryAdapter = new SearchHistoryAdapter(this);
//        mHotAdapter = new SearchHotAdapter(this);
//        mRcyHistory.setAdapter(mHistoryAdapter);
//        mRcyHot.setAdapter(mHotAdapter);
//        mPresenter.getHistoryData();
//        mPresenter.getHotData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @OnClick({R.id.ac_search_tv_cancel, R.id.ac_search_iv_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ac_search_tv_cancel:
                mEdit.setText("");
                break;
            case R.id.ac_search_iv_delete:
                break;
            default:
                break;
        }
    }
}
