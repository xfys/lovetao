package com.inner.lovetao.mineassets.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.inner.lovetao.mineassets.mvp.contract.MyOrderContract;
import com.inner.lovetao.mineassets.mvp.model.MyOrderModel;


/**
 * desc:
 * Created by xcz
 */
@Module
public abstract class MyOrderModule {

    @Binds
    abstract MyOrderContract.Model bindMyOrderModel(MyOrderModel model);
}