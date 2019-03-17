package com.inner.lovetao.mineassets.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.inner.lovetao.mineassets.mvp.contract.MineCollectionContract;
import com.inner.lovetao.mineassets.mvp.model.MineCollectionModel;


/**
 * desc:
 * Created by xcz
 */
@Module
public abstract class MineCollectionModule {

    @Binds
    abstract MineCollectionContract.Model bindMineCollectionModel(MineCollectionModel model);
}