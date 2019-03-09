package com.inner.lovetao.product_detail.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.inner.lovetao.product_detail.di.module.ProductDetailModule;
import com.inner.lovetao.product_detail.mvp.contract.ProductDetailContract;

import com.jess.arms.di.scope.ActivityScope;
import com.inner.lovetao.product_detail.mvp.ui.activity.ProductDetailActivity;


/**
 * Desc:
 * Created by xcz
 * on 2019/03/09
 */
@ActivityScope
@Component(modules = ProductDetailModule.class, dependencies = AppComponent.class)
public interface ProductDetailComponent {
    void inject(ProductDetailActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        ProductDetailComponent.Builder view(ProductDetailContract.View view);

        ProductDetailComponent.Builder appComponent(AppComponent appComponent);

        ProductDetailComponent build();
    }
}