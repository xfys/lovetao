package com.inner.lovetao.product_detail.mvp.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.inner.lovetao.R;
import com.inner.lovetao.config.ArouterConfig;
import com.inner.lovetao.product_detail.bean.ProductDetailBean;
import com.inner.lovetao.product_detail.bean.ResultsBean;
import com.inner.lovetao.product_detail.di.component.DaggerProductDetailComponent;
import com.inner.lovetao.product_detail.mvp.contract.ProductDetailContract;
import com.inner.lovetao.product_detail.mvp.presenter.ProductDetailPresenter;
import com.inner.lovetao.tab.bean.ProductItemBean;
import com.inner.lovetao.utils.CommonUtils;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.config.CommonImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * Desc:商品详情ac
 * Created by xcz
 * on 2019/03/09
 */
@Route(path = ArouterConfig.AC_PRODUCT_DETAIL)
public class ProductDetailActivity extends BaseActivity<ProductDetailPresenter> implements ProductDetailContract.View, BGABanner.Delegate<View, String>, BGABanner.Adapter<View, String> {


    @Autowired(name = ArouterConfig.ParamKey.PRODUCT_DETAIL_NUMLID)
    String productNumLid;
    @Autowired(name = ArouterConfig.ParamKey.PRODUCT_ITEM_DETAIL)
    ProductItemBean itemBean;

    @Inject
    Dialog dialog;
    /**
     * banner
     */
    @BindView(R.id.banner)
    BGABanner banner;
    /**
     * 券后价格
     */
    @BindView(R.id.tv_after_coupon_price)
    TextView tvAfterCouponPrice;
    /**
     * 淘宝价格
     */
    @BindView(R.id.tv_tb_price)
    TextView tvTbPrice;
    /**
     * 淘宝销量
     */
    @BindView(R.id.tv_tb_sale_num)
    TextView tvTbSaleNum;
    /**
     * 产品名称
     */
    @BindView(R.id.tv_product_name)
    TextView tvProductName;
    /**
     * 优惠券价格
     */
    @BindView(R.id.tv_coupon_price)
    TextView tvCouponPrice;
    /**
     * 优惠券有效期
     */
    @BindView(R.id.tv_product_end_date)
    TextView tvProductEndDate;
    /**
     * 店铺名称
     */
    @BindView(R.id.tv_shop_name)
    TextView tvShopName;
    /**
     * 产品描述
     */
    @BindView(R.id.tv_product_desc)
    TextView tvProductDesc;
    /**
     * 产品描述图片
     */
    @BindView(R.id.iv_product_bottom)
    ImageView ivProductBottom;
    /**
     * 价格
     */
    @BindView(R.id.tv_product_get_coupons)
    TextView tvProductGetCoupons;
    private ResultsBean resultBean;


    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerProductDetailComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_product_detail; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        if (!TextUtils.isEmpty(productNumLid)) {
            mPresenter.getProductDetail(productNumLid);
        } else {
            showMessage("数据错误,请返回重试");
        }
        getLine().setVisibility(View.GONE);
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
    public void getProductDetailSu(ProductDetailBean detailBean) {
        if (detailBean != null && detailBean.getResults() != null && detailBean.getResults().size() > 0) {
            resultBean = detailBean.getResults().get(0);
            if (resultBean != null) {
                setData(resultBean);
            }

        }

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    private void setData(ResultsBean resultBean) {
        if (itemBean != null) {
            if (resultBean.getSmallImages() != null) {
                banner.setAdapter(this);
                banner.setDelegate(this);
                banner.setData(R.layout.product_banner_item_view, resultBean.getSmallImages(), null);
            }


            tvAfterCouponPrice.setText(String.valueOf(Double.parseDouble(itemBean.getZkFinalPrice()) - itemBean.getCouponAmount()));
            if (itemBean.isUserType()) {
                tvTbPrice.setText("天猫价¥" + itemBean.getZkFinalPrice());
            } else {
                tvTbPrice.setText("淘宝价¥" + itemBean.getZkFinalPrice());
            }
            tvTbSaleNum.setText("销量" + String.valueOf(itemBean.getCouponTotalCount() - itemBean.getCouponRemainCount()));
            tvProductName.setText(resultBean.getTitle());
            tvCouponPrice.setText(itemBean.getCouponAmount() + "");

            //"yyyy年 MM月 dd日 HH时 mm分 ss秒"
            tvProductEndDate.setText(CommonUtils.getFormatDate("yyyy.MM.dd",
                    itemBean.getCouponStartTime())
                    + "-" + CommonUtils.getFormatDate("MM.dd", itemBean.getCouponEndTime()));
            tvShopName.setText(resultBean.getNick());
            tvProductDesc.setText(resultBean.getCatLeafName());
            if (mPresenter.getmImageLoader() != null && !TextUtils.isEmpty(resultBean.getPictUrl())) {
                mPresenter.getmImageLoader().loadImage(this,
                        CommonImageConfigImpl
                                .builder()
                                .url(resultBean.getPictUrl())
                                .isCropCenter(false)
                                .imageView(ivProductBottom)
                                .build());
            }
            tvProductGetCoupons.setText("¥" + resultBean.getZkFinalPrice() + "\n推广赚钱");
        }


    }

    @OnClick({R.id.tv_product_get, R.id.tv_product_save, R.id.tv_product_get_coupons, R.id.tv_product_to_buy})
    public void onClick(View view) {
        switch (view.getId()) {
            //领
            case R.id.tv_product_get:
                break;
            //收藏
            case R.id.tv_product_save:
                break;
            //推广
            case R.id.tv_product_get_coupons:
                break;
            //领券购买
            case R.id.tv_product_to_buy:
                break;
        }
    }


    @Override
    public void fillBannerItem(BGABanner banner, View itemView, @Nullable String model, int position) {
        if (mPresenter.getmImageLoader() != null)
            mPresenter.getmImageLoader().loadImage(itemView.getContext(),
                    CommonImageConfigImpl
                            .builder()
                            .url(model)
                            .isCropCenter(false)
                            .imageView(itemView.findViewById(R.id.iv_banner))
                            .build());
    }

    @Override
    public void onBannerItemClick(BGABanner banner, View itemView, @Nullable String model, int position) {

    }
}
