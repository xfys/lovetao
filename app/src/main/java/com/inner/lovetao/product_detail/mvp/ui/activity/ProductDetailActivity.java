package com.inner.lovetao.product_detail.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.inner.lovetao.R;
import com.inner.lovetao.config.ArouterConfig;
import com.inner.lovetao.product_detail.bean.ProductDetailBean;
import com.inner.lovetao.product_detail.di.component.DaggerProductDetailComponent;
import com.inner.lovetao.product_detail.mvp.contract.ProductDetailContract;
import com.inner.lovetao.product_detail.mvp.presenter.ProductDetailPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * Desc:商品详情ac
 * Created by xcz
 * on 2019/03/09
 */
@Route(path = ArouterConfig.AC_PRODUCT_DETAIL)
public class ProductDetailActivity extends BaseActivity<ProductDetailPresenter> implements ProductDetailContract.View {


    @Autowired(name = ArouterConfig.ParamKey.PRODUCT_DETAIL_NUMLID)
    String productNumLid;

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

    @Override
    public void getProductDetailSu(ProductDetailBean detailBean) {
        if (detailBean != null) {

        }

    }
}
