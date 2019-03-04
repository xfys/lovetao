package com.inner.lovetao.mineassets.di.module;

import com.inner.lovetao.mineassets.mvp.contract.EarningsContract;
import com.inner.lovetao.mineassets.mvp.model.EarningsModel;

import dagger.Binds;
import dagger.Module;


/**
 * ================================================
 * Description:
 * <p>
 * Created by feihaokui on 02/15/2019 11:18
 */
@Module
public abstract class EarningsModule {

    @Binds
    abstract EarningsContract.Model bindEarningsModel(EarningsModel model);
}