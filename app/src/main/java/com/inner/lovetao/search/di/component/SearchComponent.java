package com.inner.lovetao.search.di.component;

import com.inner.lovetao.search.di.module.SearchModule;
import com.inner.lovetao.search.mvp.contract.SearchContract;
import com.inner.lovetao.search.mvp.ui.activity.SearchActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.BindsInstance;
import dagger.Component;

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

@ActivityScope
@Component(modules = SearchModule.class, dependencies = AppComponent.class)
public interface SearchComponent {
    void inject(SearchActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        SearchComponent.Builder view(SearchContract.View view);

        SearchComponent.Builder appComponent(AppComponent appComponent);

        SearchComponent build();
    }
}