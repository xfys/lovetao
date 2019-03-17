package com.inner.lovetao.settings.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.inner.lovetao.settings.di.module.ContactServiceModule;
import com.inner.lovetao.settings.mvp.contract.ContactServiceContract;

import com.jess.arms.di.scope.ActivityScope;
import com.inner.lovetao.settings.mvp.ui.activity.ContactServiceActivity;


/**
 * desc:
 * Created by xcz
 */
@ActivityScope
@Component(modules = ContactServiceModule.class, dependencies = AppComponent.class)
public interface ContactServiceComponent {
    void inject(ContactServiceActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        ContactServiceComponent.Builder view(ContactServiceContract.View view);

        ContactServiceComponent.Builder appComponent(AppComponent appComponent);

        ContactServiceComponent build();
    }
}