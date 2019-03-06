package com.inner.lovetao.channel.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inner.lovetao.R;
import com.inner.lovetao.channel.presenter.GoodsFragmentPresenter;
import com.inner.lovetao.tab.bean.TestItemBean;
import com.inner.lovetao.weight.LoadMoreFooterView;
import com.inner.lovetao.weight.PullToRefreshDefaultHeader;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Author feihaokui.
 * Date 2019-01-15.
 */
public class GoodsFragment extends BaseFragment<GoodsFragmentPresenter> {

    @BindView(R.id.fm_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.pull_to_refresh_layout)
    PtrFrameLayout pullToRefreshLayout;
    private List<TestItemBean> datas = new ArrayList<>();
    private HeaderAndFooterWrapper wrapper;
    private LoadMoreFooterView footerView;

    private boolean isRefreshing;//是否正在加载
    private boolean mPullDown = true;
    private boolean noMoredata;//是否已经没有更多
    private int textIndex;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }


    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods, container, false);
        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initPullToRefresh();
        initRecycleView();
        testAddProduct();
    }

    private void initPullToRefresh() {
        PullToRefreshDefaultHeader header = new PullToRefreshDefaultHeader(mContext);
        pullToRefreshLayout.setHeaderView(header);
        pullToRefreshLayout.addPtrUIHandler(header);
        pullToRefreshLayout.setPtrHandler(new PtrDefaultHandler() {

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                testAddProduct();
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE && !isRefreshing && !noMoredata) {
                    pullUpRequest();
                }
            }
        });
    }

    private void initRecycleView() {
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(layoutManager);
        CommonAdapter<TestItemBean> adapter = new CommonAdapter<TestItemBean>(mContext, R.layout.item_goods_layout, datas) {

            @Override
            protected void convert(ViewHolder holder, TestItemBean productItemBean, int position) {
                holder.setText(R.id.tv_product_name, productItemBean.getName());
                holder.setText(R.id.tv_product_prise, productItemBean.getTbPrise());
                holder.setText(R.id.tv_product_quan, productItemBean.getQuanPrise());
                holder.setText(R.id.tv_product_already_num, productItemBean.getAlready());
                holder.setText(R.id.tv_product_quan_after, productItemBean.getQuanAferPrice());
            }
        };

        wrapper = new HeaderAndFooterWrapper(adapter);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        footerView = new LoadMoreFooterView(mContext);
        footerView.setLayoutParams(layoutParams);
        wrapper.addFootView(footerView);
        recyclerView.setAdapter(wrapper);
        wrapper.notifyDataSetChanged();
    }

    /**
     * 模拟下拉刷新
     */
    private void testAddProduct() {
        if (!mPullDown) {
            return;
        }
        datas.clear();
        noMoredata = false;
        isRefreshing = true;
        textIndex = 0;
        for (int i = 0; i < 10; i++) {
            TestItemBean testItemBean = new TestItemBean("耐克运动鞋复古限量版现实抢购最新款上架…", "淘宝价：1" + i, "已抢32478", "劵后价¥588", "¥400元劵");
            datas.add(testItemBean);
        }
        wrapper.notifyDataSetChanged();
        isRefreshing = false;
        pullToRefreshLayout.refreshComplete();
    }

    /**
     * 模拟上拉加载
     */
    private void pullUpRequest() {
        if (noMoredata) {
            return;
        }
        if (textIndex == 2) {
            noMoredata = true;
            footerView.showNoMoreState();
        } else {
            noMoredata = false;
            footerView.showLoadingState();
        }
        mPullDown = false;
        textIndex++;
        isRefreshing = true;
        for (int i = 0; i < 10; i++) {
            TestItemBean testItemBean = new TestItemBean("耐克运动鞋复古限量版现实抢购最新款上架…", "淘宝价：1" + i, "已抢32478", "劵后价¥588", "¥400元劵");
            datas.add(testItemBean);
        }
        wrapper.notifyDataSetChanged();
        isRefreshing = false;
        mPullDown = true;
        pullToRefreshLayout.refreshComplete();
    }

    @Override
    public void setData(@Nullable Object data) {

    }

}
