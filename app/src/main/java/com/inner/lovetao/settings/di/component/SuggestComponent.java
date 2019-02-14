package com.inner.lovetao.settings.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.inner.lovetao.settings.di.module.SuggestModule;
import com.inner.lovetao.settings.mvp.contract.SuggestContract;

import com.jess.arms.di.scope.ActivityScope;
import com.inner.lovetao.settings.mvp.ui.activity.SuggestActivity;


/**
 * Desc:
 * Created by xcz
 * on 2019/02/14
 */
@ActivityScope
@Component(modules = SuggestModule.class, dependencies = AppComponent.class)
public interface SuggestComponent {
    void inject(SuggestActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        SuggestComponent.Builder view(SuggestContract.View view);

        SuggestComponent.Builder appComponent(AppComponent appComponent);

        SuggestComponent build();
    }
}