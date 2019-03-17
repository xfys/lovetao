package com.inner.lovetao.settings.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.inner.lovetao.settings.di.module.MessageModule;
import com.inner.lovetao.settings.mvp.contract.MessageContract;

import com.jess.arms.di.scope.ActivityScope;
import com.inner.lovetao.settings.mvp.ui.activity.MessageActivity;


/**
 * desc:
 * Created by xcz
 */
@ActivityScope
@Component(modules = MessageModule.class, dependencies = AppComponent.class)
public interface MessageComponent {
    void inject(MessageActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MessageComponent.Builder view(MessageContract.View view);

        MessageComponent.Builder appComponent(AppComponent appComponent);

        MessageComponent build();
    }
}