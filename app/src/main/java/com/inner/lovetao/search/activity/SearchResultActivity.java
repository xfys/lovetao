package com.inner.lovetao.search.activity;

/*
 *
 *
 * 作 者 :YangFan
 *
 * 版 本 :1.0
 *
 * 创建日期 :2019/1/16      17:37
 *
 * 描 述 :搜索结果
 *
 * 修订日期 :
 */

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.inner.lovetao.R;
import com.inner.lovetao.search.adapter.SearchResultAdapter;
import com.inner.lovetao.search.mvp.SearchResultContract;
import com.inner.lovetao.search.mvp.SearchResultPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchResultActivity extends BaseActivity<SearchResultPresenter> implements SearchResultContract.Model, SearchResultContract.View {

    @BindView(R.id.ac_search_result_edt)
    EditText mEdit;
    @BindView(R.id.ac_search_result_rcy)
    RecyclerView mRcy;
    @BindView(R.id.ac_search_result_empty_layout)
    LinearLayout mEmptyLayout;
    private SearchResultAdapter mAdapter;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.ac_search_result;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
//        mToolbarTitle.setText();
        mAdapter = new SearchResultAdapter(this);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        mRcy.setLayoutManager(manager);
        mRcy.setAdapter(mAdapter);
    }

    @Override
    public void showMessage(@NonNull String message) {

    }


    @OnClick({R.id.ac_search_result_iv_back, R.id.common_recommend, R.id.common_newest, R.id.common_sales, R.id.common_price})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ac_search_result_iv_back:
                finish();
                break;
            case R.id.common_recommend://推荐
                break;
            case R.id.common_newest://最新
                break;
            case R.id.common_sales://销量
                break;
            case R.id.common_price://价格
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
