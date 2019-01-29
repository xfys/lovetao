package com.inner.lovetao.channel.di.component;

import com.inner.lovetao.channel.contract.ShelvesContract;
import com.inner.lovetao.channel.ui.activity.ShelvesActivity;
import com.inner.lovetao.channel.di.module.ShelvesModule;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;

import dagger.BindsInstance;
import dagger.Component;


/**
 * Description:
 * <p>
 * Created by feihaokui on 01/14/2019 17:32
 */
@ActivityScope
@Component(modules = ShelvesModule.class, dependencies = AppComponent.class)
public interface ShelvesComponent {
    void inject(ShelvesActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        ShelvesComponent.Builder view(ShelvesContract.View view);

        ShelvesComponent.Builder appComponent(AppComponent appComponent);

        ShelvesComponent build();
    }
}