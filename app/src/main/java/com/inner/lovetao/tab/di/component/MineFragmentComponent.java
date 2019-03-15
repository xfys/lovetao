package com.inner.lovetao.tab.di.component;

import com.inner.lovetao.tab.contract.MineFragmentContract;
import com.inner.lovetao.tab.di.module.MineFragmentModule;
import com.inner.lovetao.tab.fragment.MineFragment;
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
@Component(modules = MineFragmentModule.class, dependencies = AppComponent.class)
public interface MineFragmentComponent {
    void inject(MineFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MineFragmentComponent.Builder view(MineFragmentContract.View view);

        MineFragmentComponent.Builder appComponent(AppComponent appComponent);

        MineFragmentComponent build();
    }
}
