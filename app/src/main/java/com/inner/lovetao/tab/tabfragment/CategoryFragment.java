package com.inner.lovetao.tab.tabfragment;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inner.lovetao.R;
import com.inner.lovetao.config.ConfigInfo;
import com.inner.lovetao.tab.bean.CategoryBean;
import com.inner.lovetao.tab.bean.ProductItemBean;
import com.inner.lovetao.tab.contract.CategoryFragmentContract;
import com.inner.lovetao.tab.di.component.DaggerCategoryFragmentComponent;
import com.inner.lovetao.tab.mvp.CategoryFragmentPresenter;
import com.inner.lovetao.weight.LoadMoreFooterView;
import com.inner.lovetao.weight.PullToRefreshDefaultHeader;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.config.CommonImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Description:
 * <p>
 * Created by feihaokui on 2019-01-29.
 */
public class CategoryFragment extends BaseFragment<CategoryFragmentPresenter> implements CategoryFragmentContract.View {
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

    private List<ProductItemBean> datas = new ArrayList<>();
    private HeaderAndFooterWrapper wrapper;
    private LoadMoreFooterView footerView;
    private CategoryBean categoryBean;

    private boolean isRefreshing;//是否正在加载
    private boolean mPullDown = true;
    private boolean noMoredata;//是否已经没有更多
    private int pageNum = 1;
    private GridLayoutManager layoutManager;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerCategoryFragmentComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initPullToRefresh();
        initRecycleView();
        pullToRefreshLayout.autoRefresh();
    }

    @Override
    public void setData(@Nullable Object data) {
        if (data != null) {
            try {
                categoryBean = (CategoryBean) data;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void initPullToRefresh() {
        PullToRefreshDefaultHeader header = new PullToRefreshDefaultHeader(mContext);
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

    private void pullUpRequest() {
        if (categoryBean == null) {
            return;
        }
        if (noMoredata) {
            return;
        }
        mPullDown = false;
        pageNum++;
        isRefreshing = true;
        mPresenter.getProductList(pageNum, categoryBean.getId());
    }

    private void initRecycleView() {
        layoutManager = new GridLayoutManager(mContext, 2);
        recyclerView.setLayoutManager(layoutManager);
        CommonAdapter<ProductItemBean> adapter = new CommonAdapter<ProductItemBean>(mContext, R.layout.item_goods_layout, datas) {

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
     * 下拉刷新
     */
    private void addProduct() {
        if (categoryBean != null) {
            if (!mPullDown) {
                return;
            }
            pageNum = 1;
            mPresenter.getProductList(pageNum, categoryBean.getId());
            noMoredata = false;
            isRefreshing = true;
        }
    }


    @Override
    public void showMessage(@NonNull String message) {
        ArmsUtils.makeText(getContext(), message);
    }

    @Override
    public void getProductdataSu(List<ProductItemBean> productList) {
        if (productList == null) {
            noMoredata = true;
            pullToRefreshLayout.refreshComplete();
            footerView.showNoMoreState();
            return;
        }
        if (productList.size() < ConfigInfo.PAGE_SIZE) {
            noMoredata = true;
            footerView.showNoMoreState();
        }
        if (pageNum == 1) {
            datas.clear();
        }
        datas.addAll(productList);
        wrapper.notifyDataSetChanged();
        isRefreshing = false;
        mPullDown = true;
        pullToRefreshLayout.refreshComplete();
    }
}
