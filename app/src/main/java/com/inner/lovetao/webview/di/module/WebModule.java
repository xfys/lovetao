package com.inner.lovetao.webview.di.module;

import com.inner.lovetao.webview.mvp.contract.WebContract;
import com.inner.lovetao.webview.mvp.model.WebModel;

import dagger.Binds;
import dagger.Module;


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