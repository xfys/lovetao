package com.inner.lovetao.mineassets.di.module;

import com.inner.lovetao.mineassets.mvp.contract.EarningsDetailContract;
import com.inner.lovetao.mineassets.mvp.model.EarningsDetailModel;

import dagger.Binds;
import dagger.Module;


/**
 * ================================================
 * Description:
 * <p>
 * Created by feihaokui on 02/18/2019 11:15
 */
@Module
public abstract class EarningsDetailModule {

    @Binds
    abstract EarningsDetailContract.Model bindEarningsDetailModel(EarningsDetailModel model);
}