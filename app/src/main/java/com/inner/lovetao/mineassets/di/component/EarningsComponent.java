package com.inner.lovetao.mineassets.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.inner.lovetao.mineassets.di.module.EarningsModule;
import com.inner.lovetao.mineassets.mvp.contract.EarningsContract;

import com.jess.arms.di.scope.ActivityScope;
import com.inner.lovetao.mineassets.mvp.ui.activity.EarningsActivity;


/**
 * ================================================
 * Description:
 * <p>
 * Created by MVPArmsTemplate on 02/15/2019 11:18
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms">Star me</a>
 * <a href="https://github.com/JessYanCoding/MVPArms/wiki">See me</a>
 * <a href="https://github.com/JessYanCoding/MVPArmsTemplate">模版请保持更新</a>
 * ================================================
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