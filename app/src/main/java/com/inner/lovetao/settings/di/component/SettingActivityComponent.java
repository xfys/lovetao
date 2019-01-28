package com.inner.lovetao.settings.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.inner.lovetao.settings.di.module.SettingActivityModule;
import com.inner.lovetao.settings.mvp.contract.SettingActivityContract;

import com.jess.arms.di.scope.ActivityScope;
import com.inner.lovetao.settings.mvp.ui.activity.SettingActivity;


/**
 * desc:
 * Created by xcz
 * on 2019/01/24
 */
@ActivityScope
@Component(modules = SettingActivityModule.class, dependencies = AppComponent.class)
public interface SettingActivityComponent {
    void inject(SettingActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        SettingActivityComponent.Builder view(SettingActivityContract.View view);

        SettingActivityComponent.Builder appComponent(AppComponent appComponent);

        SettingActivityComponent build();
    }
}