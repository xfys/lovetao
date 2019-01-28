package com.inner.lovetao.search.di.module;

/*
 *
 *
 * 作 者 :YangFan
 *
 * 版 本 :1.0
 *
 * 创建日期 :2019/1/28      18:10
 *
 * 描 述 :
 *
 * 修订日期 :
 */


import com.inner.lovetao.search.mvp.contract.SearchContract;
import com.inner.lovetao.search.mvp.model.SearchModel;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class SearchModule {

    @Binds
    abstract SearchContract.Model bindSearchModel(SearchModel model);
}
