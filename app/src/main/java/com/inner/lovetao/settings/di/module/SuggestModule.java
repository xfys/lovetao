package com.inner.lovetao.settings.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.inner.lovetao.settings.mvp.contract.SuggestContract;
import com.inner.lovetao.settings.mvp.model.SuggestModel;


/**
 * Desc:
 * Created by xcz
 * on 2019/02/14
 */
@Module
public abstract class SuggestModule {

    @Binds
    abstract SuggestContract.Model bindSuggestModel(SuggestModel model);
}