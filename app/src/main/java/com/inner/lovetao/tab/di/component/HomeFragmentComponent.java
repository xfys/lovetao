package com.inner.lovetao.tab.di.component;

import com.inner.lovetao.tab.contract.HomeFragmentContract;
import com.inner.lovetao.tab.di.module.HomeFragmentModule;
import com.inner.lovetao.tab.fragment.HomePageFragment;
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
@Component(modules = HomeFragmentModule.class, dependencies = AppComponent.class)
public interface HomeFragmentComponent {
    void inject(HomePageFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        HomeFragmentComponent.Builder view(HomeFragmentContract.View view);

        HomeFragmentComponent.Builder appComponent(AppComponent appComponent);

        HomeFragmentComponent build();
    }
}
