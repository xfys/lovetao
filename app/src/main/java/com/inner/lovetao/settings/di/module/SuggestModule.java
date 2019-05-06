package com.inner.lovetao.settings.di.module;

import com.inner.lovetao.settings.mvp.contract.SuggestContract;
import com.inner.lovetao.settings.mvp.model.SuggestModel;

import dagger.Binds;
import dagger.Module;


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