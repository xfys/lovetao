package com.inner.lovetao.mineassets.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.inner.lovetao.mineassets.mvp.contract.EarningsContract;
import com.inner.lovetao.mineassets.mvp.model.EarningsModel;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 02/15/2019 11:18
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
 */
@Module
public abstract class EarningsModule {

    @Binds
    abstract EarningsContract.Model bindEarningsModel(EarningsModel model);
}