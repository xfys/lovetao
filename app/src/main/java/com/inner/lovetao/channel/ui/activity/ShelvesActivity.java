package com.inner.lovetao.channel.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.inner.lovetao.R;
import com.inner.lovetao.channel.contract.ShelvesContract;
import com.inner.lovetao.channel.di.component.DaggerShelvesComponent;
import com.inner.lovetao.channel.presenter.ShelvesPresenter;
import com.inner.lovetao.config.ArouterConfig;
import com.inner.lovetao.config.ConfigInfo;
import com.inner.lovetao.tab.bean.ProductItemBean;
import com.inner.lovetao.weight.LoadMoreFooterView;
import com.inner.lovetao.weight.PullToRefreshDefaultHeader;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.config.CommonImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * Description:商品列表
 * <p>
 * Created by feihaokui on 01/14/2019 17:37
 * Update by xcz
 */
@Route(path = ArouterConfig.AC_SHELVES)
public class ShelvesActivity extends BaseActivity<ShelvesPresenter> implements ShelvesContract.View, MultiItemTypeAdapter.OnItemClickListener {
    @BindView(R.id.common_recommend)
    TextView commonRecommend;
    @BindView(R.id.common_newest)
    TextView commonNewest;
    @BindView(R.id.common_sales)
    TextView commonSales;
    @BindView(R.id.common_price)
    TextView commonPrice;

    @BindView(R.id.fm_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.pull_to_refresh_layout)
    PtrFrameLayout pullToRefreshLayout;

    @Autowired(name = ArouterConfig.ParamKey.FROM_KEY)
    String title;
    @Autowired(name = ArouterConfig.ParamKey.ACTIVITY_ID)
    int activityId = -1;

    @Inject
    Dialog dialog;
    private GridLayoutManager layoutManager;
    private HeaderAndFooterWrapper wrapper;
    private LoadMoreFooterView footerView;
    private boolean isRefreshing;//是否正在加载
    private boolean mPullDown = true;
    private boolean noMoredata;//是否已经没有更多
    private int pageNum = 1;
    private List<ProductItemBean> datas = new ArrayList<>();

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerShelvesComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_shelves;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initCommonViewColor(commonRecommend);
        if (!TextUtils.isEmpty(title) && mToolbarTitle != null) {
            mToolbarTitle.setText(title);
        }
        initPullToRefresh();
        initRecycleView();
        pullToRefreshLayout.autoRefresh();
    }

    private void initPullToRefresh() {
        PullToRefreshDefaultHeader header = new PullToRefreshDefaultHeader(this);
        pullToRefreshLayout.setHeaderView(header);
        pullToRefreshLayout.addPtrUIHandler(header);
        pullToRefreshLayout.setPtrHandler(new PtrDefaultHandler() {

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                addProduct();
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE && (layoutManager.findLastVisibleItemPosition() == layoutManager.getItemCount() - 1) && !isRefreshing && !noMoredata) {
                    pullUpRequest();
                }
            }
        });
    }

    private void initRecycleView() {
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        CommonAdapter<ProductItemBean> adapter = new CommonAdapter<ProductItemBean>(this, R.layout.item_goods_layout, datas) {

            @Override
            protected void convert(ViewHolder holder, ProductItemBean productItemBean, int position) {
                if (productItemBean == null) {
                    return;
                }
                if (mPresenter.getmImageLoader() != null && !TextUtils.isEmpty(productItemBean.getSmallImages())) {
                    mPresenter.getmImageLoader().loadImage(mContext,
                            CommonImageConfigImpl
                                    .builder()
                                    .imageRadius(ArmsUtils.dip2px(mContext, 2))
                                    .url(productItemBean.getSmallImages())
                                    .isCropCenter(false)
                                    .imageView(holder.itemView.findViewById(R.id.iv_item_goods_pic))
                                    .build());
                }
                holder.setText(R.id.tv_product_name, productItemBean.getTitle());
                ((TextView) holder.getView(R.id.tv_product_prise)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                if (productItemBean.isUserType()) {
                    holder.setText(R.id.tv_product_prise, "天猫价¥" + productItemBean.getZkFinalPrice());
                } else {
                    holder.setText(R.id.tv_product_prise, "淘宝价¥" + productItemBean.getZkFinalPrice());
                }
                holder.setText(R.id.tv_product_quan, "¥" + productItemBean.getCouponAmount() + "元券");
                holder.setText(R.id.tv_product_already_num, "已抢" + String.valueOf(productItemBean.getCouponTotalCount() - productItemBean.getCouponRemainCount()));
                holder.setText(R.id.tv_product_quan_after, "劵后价¥" + String.valueOf(Double.parseDouble(productItemBean.getZkFinalPrice()) - productItemBean.getCouponAmount()));
            }
        };
        adapter.setOnItemClickListener(this);
        wrapper = new HeaderAndFooterWrapper(adapter);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        footerView = new LoadMoreFooterView(this);
        footerView.setLayoutParams(layoutParams);
        wrapper.addFootView(footerView);
        recyclerView.setAdapter(wrapper);
        wrapper.notifyDataSetChanged();
    }

    /**
     * 下拉刷新
     */
    private void addProduct() {
        if (!TextUtils.isEmpty(title)) {
            if (!mPullDown) {
                return;
            }
            pageNum = 1;
            getData();
            noMoredata = false;
            isRefreshing = true;
        }
    }

    private void pullUpRequest() {
        if (TextUtils.isEmpty(title)) {
            return;
        }
        if (noMoredata) {
            return;
        }
        mPullDown = false;
        pageNum++;
        isRefreshing = true;
        getData();
    }

    private void getData() {
        if (getString(R.string.home_choice_first_desc).equals(title)) {
            mPresenter.getTodayData(pageNum);

        } else if (getString(R.string.home_choice_second_desc).equals(title)) {
            mPresenter.getSale99(pageNum);

        } else if (getString(R.string.home_choice_third_desc).equals(title)) {
            mPresenter.getBigCoupon(pageNum);
        } else if (!TextUtils.isEmpty(title)) {
            if (activityId != -1) {
                mPresenter.getAcData(pageNum, activityId);
            }
        }

    }

    @Override
    public void showLoading() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
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

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean useFragment() {
        return false;
    }

    @Override
    public boolean useEventBus() {
        return false;
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
    }

    @Override
    public void getProductListSu(List<ProductItemBean> itemBeanList) {
        if (itemBeanList == null) {
            noMoredata = true;
            pullToRefreshLayout.refreshComplete();
            footerView.showNoMoreState();
            return;
        }
        if (itemBeanList.size() < ConfigInfo.PAGE_SIZE) {
            noMoredata = true;
            footerView.showNoMoreState();
        }
        if (pageNum == 1) {
            datas.clear();
        }
        datas.addAll(itemBeanList);
        wrapper.notifyDataSetChanged();
        isRefreshing = false;
        mPullDown = true;
        pullToRefreshLayout.refreshComplete();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        if (datas != null && datas.get(position) != null && !TextUtils.isEmpty(datas.get(position).getNumIid())) {
            ARouter.getInstance().build(ArouterConfig.AC_PRODUCT_DETAIL).withString(ArouterConfig.ParamKey.PRODUCT_DETAIL_NUMLID, datas.get(position).getNumIid()).navigation(this);
        }
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
