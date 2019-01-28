package com.inner.lovetao.loginregister.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.inner.lovetao.loginregister.mvp.contract.TBLoginActivityContract;
import com.inner.lovetao.loginregister.mvp.model.TBLoginActivityModel;


/**
 * desc:
 * Created by xcz
 * on 2019/01/28
 */
@Module
public abstract class TBLoginActivityModule {

    @Binds
    abstract TBLoginActivityContract.Model bindTBLoginActivityModel(TBLoginActivityModel model);
}