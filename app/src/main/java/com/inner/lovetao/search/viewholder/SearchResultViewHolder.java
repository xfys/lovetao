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
 * 描 述 :搜索结果ViewHolder
 *
 * 修订日期 :
 */

import android.view.View;
import android.widget.TextView;

import com.inner.lovetao.R;
import com.inner.lovetao.search.bean.SearchResultItemBean;
import com.jess.arms.base.BaseHolder;

import androidx.annotation.NonNull;
import butterknife.BindView;

public class SearchResultViewHolder extends BaseHolder<SearchResultItemBean> {

    @BindView(R.id.item_history_tv)
    TextView mTvDesc;


    public SearchResultViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(@NonNull SearchResultItemBean data, int position) {
        mTvDesc.setText(data.getDesc());
    }
}
