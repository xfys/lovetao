package com.inner.lovetao.settings.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.inner.lovetao.settings.mvp.contract.ContactServiceContract;
import com.inner.lovetao.settings.mvp.model.ContactServiceModel;


/**
 * desc:
 * Created by xcz
 */
@Module
public abstract class ContactServiceModule {

    @Binds
    abstract ContactServiceContract.Model bindContactServiceModel(ContactServiceModel model);
}