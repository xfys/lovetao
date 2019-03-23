package com.inner.lovetao.search.adapter;

/*
 *
 *
 * 作 者 :YangFan
 *
 * 版 本 :1.0
 *
 * 创建日期 :2019/1/14      17:39
 *
 * 描 述 :搜索结果Adapter
 *
 * 修订日期 :
 */

import android.content.Context;
import android.view.View;

import com.inner.lovetao.R;
import com.inner.lovetao.search.bean.SearchResultItemBean;
import com.inner.lovetao.search.viewholder.SearchResultViewHolder;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import androidx.annotation.NonNull;

public class SearchResultAdapter extends DefaultAdapter<SearchResultItemBean> {

    public SearchResultAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseHolder<SearchResultItemBean> getHolder(@NonNull View v, int viewType) {
        return new SearchResultViewHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_search_history_layout;
    }

    public void cleanData() {
        mData.clear();
        notifyDataSetChanged();
    }
}
