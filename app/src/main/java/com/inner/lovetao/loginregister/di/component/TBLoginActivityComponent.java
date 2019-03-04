package com.inner.lovetao.loginregister.di.component;

import com.inner.lovetao.loginregister.di.module.TBLoginActivityModule;
import com.inner.lovetao.loginregister.mvp.contract.TBLoginActivityContract;
import com.inner.lovetao.loginregister.mvp.ui.activity.TBLoginActivity;
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
@Component(modules = TBLoginActivityModule.class, dependencies = AppComponent.class)
public interface TBLoginActivityComponent {
    void inject(TBLoginActivity activity);
    @Component.Builder
    interface Builder {
        @BindsInstance
        TBLoginActivityComponent.Builder view(TBLoginActivityContract.View view);
        TBLoginActivityComponent.Builder appComponent(AppComponent appComponent);
        TBLoginActivityComponent build();
    }
}