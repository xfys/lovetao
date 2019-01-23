package com.inner.lovetao.channel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.inner.lovetao.R;
import com.inner.lovetao.channel.model.GoodsItemModel;
import com.inner.lovetao.channel.viewholder.GoodsViewHolder;
import com.jess.arms.base.BaseHolder;
import com.jess.arms.base.DefaultAdapter;

/**
 * Author feihaokui.
 * Date 2019-01-15.
 */
public class GoodsListAdapter extends DefaultAdapter<GoodsItemModel> {

    public GoodsListAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseHolder<GoodsItemModel> getHolder(@NonNull View v, int viewType) {
        return new GoodsViewHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_goods_layout;
    }
}
