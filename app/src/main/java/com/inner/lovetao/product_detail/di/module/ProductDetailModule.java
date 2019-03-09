package com.inner.lovetao.product_detail.di.module;

import android.app.Dialog;

import com.inner.lovetao.product_detail.mvp.contract.ProductDetailContract;
import com.inner.lovetao.product_detail.mvp.model.ProductDetailModel;
import com.inner.lovetao.weight.dialog.ProgresDialog;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;


/**
 * Desc:
 * Created by xcz
 * on 2019/03/09
 */
@Module
public abstract class ProductDetailModule {

    @Binds
    abstract ProductDetailContract.Model bindProductDetailModel(ProductDetailModel model);

    @ActivityScope
    @Provides
    static Dialog provideDialog(ProductDetailContract.View view) {
        return new ProgresDialog(view.getActivity());
    }
}