package com.inner.lovetao.search.mvp.ui.activity;

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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.inner.lovetao.R;
import com.inner.lovetao.config.ArouterConfig;
import com.inner.lovetao.config.ConfigInfo;
import com.inner.lovetao.config.RefreshConfig;
import com.inner.lovetao.search.di.component.DaggerSearchResultComponent;
import com.inner.lovetao.search.mvp.contract.SearchResultContract;
import com.inner.lovetao.search.mvp.presenter.SearchResultPresenter;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * desc:
 * Created by xcz
 */
@Route(path = ArouterConfig.AC_SEARCH_RESULT)
public class SearchResultActivity extends BaseActivity<SearchResultPresenter> implements SearchResultContract.View, MultiItemTypeAdapter.OnItemClickListener {
    @BindView(R.id.ac_search_result_edt)
    EditText mEdit;
    @BindView(R.id.ac_search_result_rcy)
    RecyclerView mRcy;
    @BindView(R.id.ac_search_result_empty_layout)
    LinearLayout mEmptyLayout;
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

    private String sortName;
    private String sortOrder;
    private boolean isRefreshing;//是否正在加载
    private boolean mPullDown = true;
    private boolean noMoredata;//是否已经没有更多
    private int pageNum = 1;
    private GridLayoutManager layoutManager;
    @Autowired(name = ArouterConfig.ParamKey.FROM_KEY)
    String title;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerSearchResultComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.ac_search_result;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mEdit.setText(title);
        initCommonViewColor(commonRecommend);
        sortName = RefreshConfig.SORT_RECOMMEND;
        sortOrder = RefreshConfig.SORT_ASCENDING;
        initPullToRefresh();
        initRecycleView();
        pullToRefreshLayout.autoRefresh();
    }

    private void initRecycleView() {
        layoutManager = new GridLayoutManager(this, 2);
        mRcy.setLayoutManager(layoutManager);
        CommonAdapter<ProductItemBean> adapter = new CommonAdapter<ProductItemBean>(this, R.layout.item_goods_layout, datas) {

            @Override
            protected void convert(ViewHolder holder, ProductItemBean productItemBean, int position) {
                if (productItemBean == null) {
                    return;
                }
                if (mPresenter.getImageLoader() != null && !TextUtils.isEmpty(productItemBean.getSmallImages())) {
                    mPresenter.getImageLoader().loadImage(mContext,
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
                holder.setText(R.id.tv_product_quan, String.valueOf(productItemBean.getCouponAmount()));
                holder.setText(R.id.tv_product_already_num, "已抢" + String.valueOf(productItemBean.getCouponTotalCount() - productItemBean.getCouponRemainCount()));
                BigDecimal b = new BigDecimal(Double.parseDouble(productItemBean.getZkFinalPrice()) - productItemBean.getCouponAmount());
                holder.setText(R.id.tv_product_quan_after, "劵后价¥" + String.valueOf(b.setScale(2, BigDecimal.ROUND_DOWN).doubleValue()));
            }
        };
        adapter.setOnItemClickListener(this);
        wrapper = new HeaderAndFooterWrapper(adapter);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        footerView = new LoadMoreFooterView(this);
        footerView.setLayoutParams(layoutParams);
        wrapper.addFootView(footerView);
        mRcy.setAdapter(wrapper);
        wrapper.notifyDataSetChanged();
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
        mRcy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE && (layoutManager.findLastVisibleItemPosition() == layoutManager.getItemCount() - 1) && !isRefreshing && !noMoredata) {
                    pullUpRequest();
                }
            }
        });
    }


    @OnClick({R.id.ac_search_result_iv_back, R.id.common_recommend, R.id.common_newest, R.id.common_sales, R.id.common_price,R.id.ac_search_result_tv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ac_search_result_iv_back:
                killMyself();
                break;
            case R.id.ac_search_result_tv_search:
                title = String.valueOf(mEdit.getText());
                sortName = RefreshConfig.SORT_RECOMMEND;
                initCommonViewColor(commonRecommend);
                pullToRefreshLayout.autoRefresh();
                break;
            case R.id.common_recommend://推荐
                sortName = RefreshConfig.SORT_RECOMMEND;
                initCommonViewColor(commonRecommend);
                pullToRefreshLayout.autoRefresh();
                break;
            case R.id.common_newest://最新
                sortName = RefreshConfig.SORT_NEWEST;
                initCommonViewColor(commonNewest);
                pullToRefreshLayout.autoRefresh();
                break;
            case R.id.common_sales://销量
                sortName = RefreshConfig.SORT_SALES;
                initCommonViewColor(commonSales);
                pullToRefreshLayout.autoRefresh();
                break;
            case R.id.common_price://价格
                sortName = RefreshConfig.SORT_PRICE;
                if (TextUtils.equals(sortOrder, RefreshConfig.SORT_ASCENDING)) {
                    sortOrder = RefreshConfig.SORT_DESCENDING;
                } else {
                    sortOrder = RefreshConfig.SORT_ASCENDING;
                }
                initCommonViewColor(commonPrice);
                pullToRefreshLayout.autoRefresh();
                break;
            default:
                break;
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

    /**
     * 下拉刷新
     */
    private void addProduct() {
        if (!TextUtils.isEmpty(title)) {
            if (!mPullDown) {
                return;
            }
            pageNum = 1;
            getData(sortName);
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
        getData(sortName);
    }

    private void getData(String name) {
        if (!TextUtils.isEmpty(title)) {
            switch (name) {
                case RefreshConfig.SORT_RECOMMEND:
                    mPresenter.getSearchData(pageNum, title);
                    break;
                case RefreshConfig.SORT_NEWEST:
                    mPresenter.getSearchDataForSort(pageNum, title, RefreshConfig.SORT_NEWEST);
                    break;
                case RefreshConfig.SORT_SALES:
                    mPresenter.getSearchDataForSort(pageNum, title, RefreshConfig.SORT_SALES);
                    break;
                case RefreshConfig.SORT_PRICE:
                    mPresenter.getSearchDataForSorts(pageNum, title, RefreshConfig.SORT_PRICE, sortOrder);
                    break;
                default:
                    mPresenter.getSearchData(pageNum, title);
                    break;
            }
        }
    }


    @Override
    public void getSearchResultData(List<ProductItemBean> searchResultList) {
        if (searchResultList == null) {
            noMoredata = true;
            pullToRefreshLayout.refreshComplete();
            footerView.showNoMoreState();
            return;
        }
        if (searchResultList.size() < ConfigInfo.PAGE_SIZE) {
            noMoredata = true;
            footerView.showNoMoreState();
        }
        if (pageNum == 1) {
            datas.clear();
        }
        datas.addAll(searchResultList);
        wrapper.notifyDataSetChanged();
        isRefreshing = false;
        mPullDown = true;
        pullToRefreshLayout.refreshComplete();
    }


    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        if (datas != null && datas.get(position) != null && !TextUtils.isEmpty(datas.get(position).getNumIid())) {
            ARouter.getInstance()
                    .build(ArouterConfig.AC_PRODUCT_DETAIL)
                    .withString(ArouterConfig.ParamKey.PRODUCT_DETAIL_NUMLID, datas.get(position).getNumIid())
                    .withSerializable(ArouterConfig.ParamKey.PRODUCT_ITEM_DETAIL, datas.get(position))
                    .navigation(this);
        }
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }

    private void initCommonViewColor(@NonNull TextView view) {
        commonNewest.setTextColor(getResources().getColor(R.color.color_444444));
        commonPrice.setTextColor(getResources().getColor(R.color.color_444444));
        commonSales.setTextColor(getResources().getColor(R.color.color_444444));
        commonRecommend.setTextColor(getResources().getColor(R.color.color_444444));
        view.setTextColor(getResources().getColor(R.color.color_E83F5C));
    }

}
