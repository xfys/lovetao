package com.inner.lovetao.mineassets.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.inner.lovetao.mineassets.di.module.MineCollectionModule;
import com.inner.lovetao.mineassets.mvp.contract.MineCollectionContract;

import com.jess.arms.di.scope.ActivityScope;
import com.inner.lovetao.mineassets.mvp.ui.activity.MineCollectionActivity;


/**
 * desc:
 * Created by xcz
 */
@ActivityScope
@Component(modules = MineCollectionModule.class, dependencies = AppComponent.class)
public interface MineCollectionComponent {
    void inject(MineCollectionActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MineCollectionComponent.Builder view(MineCollectionContract.View view);

        MineCollectionComponent.Builder appComponent(AppComponent appComponent);

        MineCollectionComponent build();
    }
}