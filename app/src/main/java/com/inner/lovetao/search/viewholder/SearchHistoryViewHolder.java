package com.inner.lovetao.search.viewholder;

/*
 *
 *
 * 作 者 :YangFan
 *
 * 版 本 :1.0
 *
 * 创建日期 :2019/1/14      17:54
 *
 * 描 述 :搜索历史ViewHolder
 *
 * 修订日期 :
 */

import android.support.annotation.NonNull;
import android.view.View;

import com.inner.lovetao.beans.response.search.SearchHistoryItemBean;
import com.jess.arms.base.BaseHolder;

public class SearchHistoryViewHolder extends BaseHolder<SearchHistoryItemBean> {

    public SearchHistoryViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(@NonNull SearchHistoryItemBean data, int position) {

    }
}
