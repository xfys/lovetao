package com.inner.lovetao.mineassets.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.inner.lovetao.mineassets.di.module.MyOrderModule;
import com.inner.lovetao.mineassets.mvp.contract.MyOrderContract;

import com.jess.arms.di.scope.ActivityScope;
import com.inner.lovetao.mineassets.mvp.ui.activity.MyOrderActivity;


/**
 * desc:
 * Created by xcz
 */
@ActivityScope
@Component(modules = MyOrderModule.class, dependencies = AppComponent.class)
public interface MyOrderComponent {
    void inject(MyOrderActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MyOrderComponent.Builder view(MyOrderContract.View view);

        MyOrderComponent.Builder appComponent(AppComponent appComponent);

        MyOrderComponent build();
    }
}