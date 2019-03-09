package com.inner.lovetao.product_detail.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.inner.lovetao.product_detail.mvp.contract.ProductDetailContract;
import com.inner.lovetao.product_detail.mvp.model.ProductDetailModel;


/**
 * Desc:
 * Created by xcz
 * on 2019/03/09
 */
@Module
public abstract class ProductDetailModule {

    @Binds
    abstract ProductDetailContract.Model bindProductDetailModel(ProductDetailModel model);
}