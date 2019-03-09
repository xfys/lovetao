package com.inner.lovetao.tab.di.component;

import com.inner.lovetao.tab.contract.WlfareServiceFragmentContract;
import com.inner.lovetao.tab.di.module.WlfareServiceFragmentModule;
import com.inner.lovetao.tab.fragment.WlfareServiceFragment;
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
@Component(modules = WlfareServiceFragmentModule.class, dependencies = AppComponent.class)
public interface WlfareServiceFragmentComponent {
    void inject(WlfareServiceFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        WlfareServiceFragmentComponent.Builder view(WlfareServiceFragmentContract.View view);

        WlfareServiceFragmentComponent.Builder appComponent(AppComponent appComponent);

        WlfareServiceFragmentComponent build();
    }
}
