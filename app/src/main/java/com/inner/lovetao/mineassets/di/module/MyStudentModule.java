package com.inner.lovetao.mineassets.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.inner.lovetao.mineassets.mvp.contract.MyStudentContract;
import com.inner.lovetao.mineassets.mvp.model.MyStudentModel;


/**
 * desc:
 * Created by xcz
 */
@Module
public abstract class MyStudentModule {

    @Binds
    abstract MyStudentContract.Model bindMyStudentModel(MyStudentModel model);
}