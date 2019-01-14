package com.inner.lovetao.search.adapter;

/*
 *
 *
 * 作 者 :YangFan
 *
 * 版 本 :1.0
 *
 * 创建日期 :2019/1/14      17:52
 *
 * 描 述 :
 *
 * 修订日期 :
 */

import android.support.annotation.NonNull;
import android.view.View;

import com.inner.lovetao.beans.response.search.SearchHotItemBean;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

import java.util.List;

public class SearchHotAdapter extends DefaultAdapter<SearchHotItemBean> {

    public SearchHotAdapter(List<SearchHotItemBean> data) {
        super(data);
    }

    @NonNull
    @Override
    public BaseHolder<SearchHotItemBean> getHolder(@NonNull View v, int viewType) {
        return null;
    }

    @Override
    public int getLayoutId(int viewType) {
        return 0;
    }
}
