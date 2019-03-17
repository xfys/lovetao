package com.inner.lovetao.mineassets.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.inner.lovetao.R;
import com.inner.lovetao.config.ArouterConfig;
import com.inner.lovetao.mineassets.di.component.DaggerEarningsDetailComponent;
import com.inner.lovetao.mineassets.mvp.bean.EarningsDetailBean;
import com.inner.lovetao.mineassets.mvp.contract.EarningsDetailContract;
import com.inner.lovetao.mineassets.mvp.presenter.EarningsDetailPresenter;
import com.inner.lovetao.weight.PullToRefreshDefaultHeader;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * Description:收益明细
 * <p>
 * Created by feihaokui on 02/18/2019
 */
@Route(path = ArouterConfig.AC_EARNING_DETAIL)
public class EarningsDetailActivity extends BaseActivity<EarningsDetailPresenter> implements EarningsDetailContract.View {

    @BindView(R.id.fm_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.pull_to_refresh_layout)
    PtrFrameLayout pullToRefreshLayout;

    private List<EarningsDetailBean> datas = new ArrayList<>();
    private HeaderAndFooterWrapper wrapper;
//    private LoadMoreFooterView footerView;


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerEarningsDetailComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_earnings_detail; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initPullToRefresh();
        initRecycleView();
        testAddProduct();
    }


    private void initPullToRefresh() {
        PullToRefreshDefaultHeader header = new PullToRefreshDefaultHeader(this);
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
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                pullToRefreshLayout.refreshComplete();
            }
        });
    }

    private void initRecycleView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        CommonAdapter<EarningsDetailBean> adapter = new CommonAdapter<EarningsDetailBean>(this, R.layout.item_earnings_detail_layout, datas) {

            @Override
            protected void convert(ViewHolder holder, EarningsDetailBean detailBean, int position) {

            }
        };

        wrapper = new HeaderAndFooterWrapper(adapter);
//        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        footerView = new LoadMoreFooterView(this);
//        footerView.setLayoutParams(layoutParams);
//        wrapper.addFootView(footerView);
        recyclerView.setAdapter(wrapper);
        wrapper.notifyDataSetChanged();
    }

    /**
     * 模拟下拉刷新
     */
    private void testAddProduct() {
        datas.clear();
//        for (int i = 0; i < 10; i++) {
//            EarningsDetailBean detailBean = new EarningsDetailBean("徒弟购物收益", "2019-01-12 12:00:00", "+0.88元");
//            datas.add(detailBean);
//        }
        wrapper.notifyDataSetChanged();
        pullToRefreshLayout.refreshComplete();
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

}
