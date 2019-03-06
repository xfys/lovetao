package com.inner.lovetao.webview.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.inner.lovetao.webview.di.module.WebModule;
import com.inner.lovetao.webview.mvp.contract.WebContract;

import com.jess.arms.di.scope.ActivityScope;
import com.inner.lovetao.webview.mvp.ui.activity.WebActivity;


/**
 * Desc:
 * Created by xcz
 * on 2019/03/06
 */
@ActivityScope
@Component(modules = WebModule.class, dependencies = AppComponent.class)
public interface WebComponent {
    void inject(WebActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        WebComponent.Builder view(WebContract.View view);

        WebComponent.Builder appComponent(AppComponent appComponent);

        WebComponent build();
    }
}