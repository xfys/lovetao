package com.inner.lovetao.tab.di.component;

import com.inner.lovetao.tab.contract.CategoryFragmentContract;
import com.inner.lovetao.tab.di.module.CategoryFragmentModule;
import com.inner.lovetao.tab.tabfragment.CategoryFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.BindsInstance;
import dagger.Component;

/**
 * desc:
 * Created by xcz
 * on 2019/3/5.
 */
@ActivityScope
@Component(modules = CategoryFragmentModule.class, dependencies = AppComponent.class)
public interface CategoryFragmentComponent {
    void inject(CategoryFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        CategoryFragmentComponent.Builder view(CategoryFragmentContract.View view);

        CategoryFragmentComponent.Builder appComponent(AppComponent appComponent);

        CategoryFragmentComponent build();
    }
}
