package com.inner.lovetao.settings.di.module;

import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

import com.inner.lovetao.settings.mvp.contract.MessageContract;
import com.inner.lovetao.settings.mvp.model.MessageModel;


/**
 * desc:
 * Created by xcz
 */
@Module
public abstract class MessageModule {

    @Binds
    abstract MessageContract.Model bindMessageModel(MessageModel model);
}