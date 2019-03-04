package com.inner.lovetao.mineassets.di.component;

import com.inner.lovetao.mineassets.di.module.EarningsDetailModule;
import com.inner.lovetao.mineassets.mvp.contract.EarningsDetailContract;
import com.inner.lovetao.mineassets.mvp.ui.activity.EarningsDetailActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.BindsInstance;
import dagger.Component;


/**
 * ================================================
 * Description:
 * <p>
 * Created by feihaokui on 02/18/2019 11:15
 */
@ActivityScope
@Component(modules = EarningsDetailModule.class, dependencies = AppComponent.class)
public interface EarningsDetailComponent {
    void inject(EarningsDetailActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        EarningsDetailComponent.Builder view(EarningsDetailContract.View view);

        EarningsDetailComponent.Builder appComponent(AppComponent appComponent);

        EarningsDetailComponent build();
    }
}