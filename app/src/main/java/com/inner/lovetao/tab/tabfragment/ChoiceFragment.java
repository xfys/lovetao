package com.inner.lovetao.tab.tabfragment;

import android.app.Dialog;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.inner.lovetao.R;
import com.inner.lovetao.config.ArouterConfig;
import com.inner.lovetao.config.BannerType;
import com.inner.lovetao.config.ConfigInfo;
import com.inner.lovetao.tab.bean.BannerBean;
import com.inner.lovetao.tab.bean.FourAcBean;
import com.inner.lovetao.tab.bean.ProductItemBean;
import com.inner.lovetao.tab.contract.ChoicFragmentContract;
import com.inner.lovetao.tab.di.component.DaggerChoiceFragmentComponent;
import com.inner.lovetao.tab.mvp.ChoiceFragmentPresenter;
import com.inner.lovetao.tab.view.ChoiceBannerView;
import com.inner.lovetao.tab.view.RecommendTwoView;
import com.inner.lovetao.tab.view.RecommendView;
import com.inner.lovetao.weight.LoadMoreFooterView;
import com.inner.lovetao.weight.PullToRefreshDefaultHeader;
import com.jess.arms.base.BaseFragment;
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

import javax.inject.Inject;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * desc:精选
 * Created by xcz
 * on 2019/1/22.
 */
public class ChoiceFragment extends BaseFragment<ChoiceFragmentPresenter> implements ChoicFragmentContract.View, MultiItemTypeAdapter.OnItemClickListener {
    @BindView(R.id.pull_to_refresh_layout)
    PtrFrameLayout ptrFrameLayout;
    @BindView(R.id.fm_recyclerView)
    RecyclerView recyclerView;
    @Inject
    Dialog mDialog;
    private List<ProductItemBean> datas = new ArrayList<>();
    private ChoiceBannerView bannerView;
    private HeaderAndFooterWrapper headerAndFooterWrapper;
    private LinearLayoutManager layoutManager;
    private boolean isRefreshing;//是否正在加载
    private boolean mPullDown = true;
    private boolean noMoredata;//是否已经没有更多
    private int pageNum = 1;
    private LoadMoreFooterView loadMoreFooterView;
    private RecommendTwoView recommendTwoView;
    private RecommendView recommendView;


    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerChoiceFragmentComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choice, container, false);
        return view;
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initPullToRefresh();
        initRecycleView();
        mPresenter.getBanner(BannerType.HOME_BANNER.getType());
        mPresenter.getBanner(BannerType.HMOE_NAVIGATION.getType());
        mPresenter.getJingPinData(pageNum, 5);
        mPresenter.getFourAc();
        addProduct();
    }


    @Override
    public void setData(@Nullable Object data) {

    }

    private void initPullToRefresh() {
        PullToRefreshDefaultHeader header = new PullToRefreshDefaultHeader(mContext);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //下拉刷新回调
                mPresenter.getBanner(BannerType.HOME_BANNER.getType());
                mPresenter.getBanner(BannerType.HMOE_NAVIGATION.getType());
                //精选好物
                mPresenter.getJingPinData(pageNum, 5);
                mPresenter.getFourAc();
                addProduct();
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE && (layoutManager.findLastVisibleItemPosition() == layoutManager.getItemCount() - 1) && !isRefreshing && !noMoredata) {
                    pullUpRequest();
                }
            }
        });
    }

    private void initRecycleView() {
        layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        CommonAdapter<ProductItemBean> adapter = new CommonAdapter<ProductItemBean>(mContext, R.layout.item_home_choice, datas) {
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
                                    .imageView(holder.itemView.findViewById(R.id.iv_product))
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
        headerAndFooterWrapper = new HeaderAndFooterWrapper(adapter);
        loadMoreFooterView = new LoadMoreFooterView(mContext);
        loadMoreFooterView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        bannerView = new ChoiceBannerView(mContext);
        recommendView = new RecommendView(mContext);
        recommendView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        recommendTwoView = new RecommendTwoView(mContext);
        headerAndFooterWrapper.addHeaderView(bannerView);
        headerAndFooterWrapper.addHeaderView(recommendView);
        headerAndFooterWrapper.addHeaderView(recommendTwoView);
        headerAndFooterWrapper.addFootView(loadMoreFooterView);
        recyclerView.setAdapter(headerAndFooterWrapper);
        headerAndFooterWrapper.notifyDataSetChanged();
    }

    /**
     * 模拟下拉刷新
     */
    private void addProduct() {
        if (!mPullDown) {
            return;
        }
        pageNum = 1;
        mPresenter.getJingPinData(pageNum, 5);
        noMoredata = false;
        isRefreshing = true;
    }

    /**
     * 模拟上拉加载
     */
    private void pullUpRequest() {
        if (noMoredata) {
            return;
        }
        mPullDown = false;
        pageNum++;
        isRefreshing = true;
        mPresenter.getJingPinData(pageNum, 5);

    }


    @Override
    public void showMessage(@NonNull String message) {
        ArmsUtils.makeText(getContext(), message);
    }

    @Override
    public void showLoading() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void getBannerDataSu(int type, List<BannerBean> bannerBeanList) {
        if (type == 1) {
            bannerView.setData(bannerBeanList);
        } else if (type == 2) {
            recommendView.setData(bannerBeanList);
        }

    }

    @Override
    public void getFourAcSu(List<FourAcBean> fourAcBeanList) {
        if (fourAcBeanList != null) {
            recommendTwoView.setData(fourAcBeanList);
        }
    }

    @Override
    public void getJPdataSu(List<ProductItemBean> jingPingList) {
        if (jingPingList.size() < ConfigInfo.PAGE_SIZE) {
            noMoredata = true;
            loadMoreFooterView.showNoMoreState();
        }
        if (pageNum == 1) {
            datas.clear();
        }
        if (jingPingList != null) {
            datas.addAll(jingPingList);
        }
        headerAndFooterWrapper.notifyDataSetChanged();
        isRefreshing = false;
        mPullDown = true;
        ptrFrameLayout.refreshComplete();

    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        if (datas != null && datas.get(position) != null && !TextUtils.isEmpty(datas.get(position).getNumIid())) {
            ARouter.getInstance()
                    .build(ArouterConfig.AC_PRODUCT_DETAIL)
                    .withString(ArouterConfig.ParamKey.PRODUCT_DETAIL_NUMLID, datas.get(position).getNumIid())
                    .withSerializable(ArouterConfig.ParamKey.PRODUCT_ITEM_DETAIL, datas.get(position))
                    .navigation(mContext);
        }
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        return false;
    }
}
