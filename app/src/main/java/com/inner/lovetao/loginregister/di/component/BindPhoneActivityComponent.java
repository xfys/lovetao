package com.inner.lovetao.loginregister.di.component;

import com.inner.lovetao.loginregister.di.module.BindPhoneActivityModule;
import com.inner.lovetao.loginregister.mvp.contract.BindPhoneActivityContract;
import com.inner.lovetao.loginregister.mvp.ui.activity.BindPhoneActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.BindsInstance;
import dagger.Component;
/**
 * desc:
 * Created by xcz
 * on 2019/01/28
 */
@ActivityScope
@Component(modules = BindPhoneActivityModule.class, dependencies = AppComponent.class)
public interface BindPhoneActivityComponent {
    void inject(BindPhoneActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        BindPhoneActivityComponent.Builder view(BindPhoneActivityContract.View view);

        BindPhoneActivityComponent.Builder appComponent(AppComponent appComponent);

        BindPhoneActivityComponent build();
    }
}