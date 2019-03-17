package com.inner.lovetao.mineassets.di.component;

import dagger.BindsInstance;
import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.inner.lovetao.mineassets.di.module.MyStudentModule;
import com.inner.lovetao.mineassets.mvp.contract.MyStudentContract;

import com.jess.arms.di.scope.ActivityScope;
import com.inner.lovetao.mineassets.mvp.ui.activity.MyStudentActivity;


/**
 * desc:
 * Created by xcz
 */
@ActivityScope
@Component(modules = MyStudentModule.class, dependencies = AppComponent.class)
public interface MyStudentComponent {
    void inject(MyStudentActivity activity);

    @Component.Builder
    interface Builder {
        @BindsInstance
        MyStudentComponent.Builder view(MyStudentContract.View view);

        MyStudentComponent.Builder appComponent(AppComponent appComponent);

        MyStudentComponent build();
    }
}