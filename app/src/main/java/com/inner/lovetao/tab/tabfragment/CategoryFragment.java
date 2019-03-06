package com.inner.lovetao.tab.tabfragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inner.lovetao.R;
import com.inner.lovetao.tab.bean.TestItemBean;
import com.inner.lovetao.tab.mvp.CategoryFragmentPresenter;
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
import butterknife.OnClick;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Description:
 * <p>
 * Created by feihaokui on 2019-01-29.
 */
public class CategoryFragment extends BaseFragment<CategoryFragmentPresenter> {
    @BindView(R.id.fm_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.pull_to_refresh_layout)
    PtrFrameLayout pullToRefreshLayout;
    @BindView(R.id.common_recommend)
    TextView commonRecommend;
    @BindView(R.id.common_newest)
    TextView commonNewest;
    @BindView(R.id.common_sales)
    TextView commonSales;
    @BindView(R.id.common_price)
    TextView commonPrice;
    Unbinder unbinder;

    private List<TestItemBean> datas = new ArrayList<>();
    private HeaderAndFooterWrapper wrapper;
    private LoadMoreFooterView footerView;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initPullToRefresh();
        initRecycleView();
        testAddProduct();
    }

    @Override
    public void setData(@Nullable Object data) {

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


    @OnClick({R.id.common_recommend, R.id.common_newest, R.id.common_sales, R.id.common_price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_recommend:
                initCommonViewColor(commonRecommend);
                break;
            case R.id.common_newest:
                initCommonViewColor(commonNewest);
                break;
            case R.id.common_sales:
                initCommonViewColor(commonSales);
                break;
            case R.id.common_price:
                initCommonViewColor(commonPrice);
                break;
        }
    }

    private void initCommonViewColor(@NonNull TextView view) {
        commonNewest.setTextColor(getResources().getColor(R.color.color_444444));
        commonPrice.setTextColor(getResources().getColor(R.color.color_444444));
        commonSales.setTextColor(getResources().getColor(R.color.color_444444));
        commonRecommend.setTextColor(getResources().getColor(R.color.color_444444));
        view.setTextColor(getResources().getColor(R.color.color_E83F5C));
        pullToRefreshLayout.autoRefresh();
    }


    /**
     * 模拟下拉刷新
     */
    private void testAddProduct() {
        for (int i = 0; i < 10; i++) {
            TestItemBean testItemBean = new TestItemBean("耐克运动鞋复古限量版现实抢购最新款上架…", "淘宝价：1" + i, "已抢32478", "劵后价¥588", "¥400元劵");
            datas.add(testItemBean);
        }
        wrapper.notifyDataSetChanged();
        pullToRefreshLayout.refreshComplete();
    }

}
