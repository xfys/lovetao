package com.inner.lovetao.mineassets.di.component;

import com.inner.lovetao.mineassets.di.module.EarningsModule;
import com.inner.lovetao.mineassets.mvp.contract.EarningsContract;
import com.inner.lovetao.mineassets.mvp.ui.activity.EarningsActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.BindsInstance;
import dagger.Component;


/**
 * ================================================
 * Description:
 * <p>
 * Created by feihaokui on 02/15/2019 11:18
 */
@ActivityScope
@Component(modules = EarningsModule.class, dependencies = AppComponent.class)
public interface EarningsComponent {
    void inject(EarningsActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        EarningsComponent.Builder view(EarningsContract.View view);

        EarningsComponent.Builder appComponent(AppComponent appComponent);

        EarningsComponent build();
    }
}