package com.inner.lovetao.webview.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.inner.lovetao.webview.mvp.contract.WebContract;
import com.inner.lovetao.webview.mvp.model.WebModel;


/**
 * Desc:
 * Created by xcz
 * on 2019/03/06
 */
@Module
public abstract class WebModule {

    @Binds
    abstract WebContract.Model bindWebModel(WebModel model);
}