package com.inner.lovetao.search.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.inner.lovetao.search.mvp.contract.SearchResultContract;
import com.inner.lovetao.search.mvp.model.SearchResultModel;


/**
 * desc:
 * Created by xcz
 */
@Module
public abstract class SearchResultModule {

    @Binds
    abstract SearchResultContract.Model bindSearchResultModel(SearchResultModel model);
}