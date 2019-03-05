package com.inner.lovetao.tab.di.component;

import com.inner.lovetao.tab.contract.ChoicFragmentContract;
import com.inner.lovetao.tab.di.module.ChoiceFragmentModule;
import com.inner.lovetao.tab.tabfragment.ChoiceFragment;
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
@Component(modules = ChoiceFragmentModule.class, dependencies = AppComponent.class)
public interface ChoiceFragmentComponent {
    void inject(ChoiceFragment fragment);

    @Component.Builder
    interface Builder {
        @BindsInstance
        ChoiceFragmentComponent.Builder view(ChoicFragmentContract.View view);

        ChoiceFragmentComponent.Builder appComponent(AppComponent appComponent);

        ChoiceFragmentComponent build();
    }
}
