package com.inner.lovetao.search.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.inner.lovetao.search.di.module.SearchResultModule;
import com.inner.lovetao.search.mvp.contract.SearchResultContract;

import com.jess.arms.di.scope.ActivityScope;
import com.inner.lovetao.search.mvp.ui.activity.SearchResultActivity;


/**
 * desc:
 * Created by xcz
 */
@ActivityScope
@Component(modules = SearchResultModule.class, dependencies = AppComponent.class)
public interface SearchResultComponent {
    void inject(SearchResultActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        SearchResultComponent.Builder view(SearchResultContract.View view);

        SearchResultComponent.Builder appComponent(AppComponent appComponent);

        SearchResultComponent build();
    }
}