package com.inner.lovetao.settings.di.module;

import com.inner.lovetao.settings.mvp.contract.ContactServiceContract;
import com.inner.lovetao.settings.mvp.model.ContactServiceModel;

import dagger.Binds;
import dagger.Module;


/**
 * desc:
 * Created by xcz
 */
@Module
public abstract class ContactServiceModule {

    @Binds
    abstract ContactServiceContract.Model bindContactServiceModel(ContactServiceModel model);
}