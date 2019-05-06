package com.inner.lovetao.settings.di.module;

import com.inner.lovetao.settings.mvp.contract.MessageContract;
import com.inner.lovetao.settings.mvp.model.MessageModel;

import dagger.Binds;
import dagger.Module;


/**
 * desc:
 * Created by xcz
 */
@Module
public abstract class MessageModule {

    @Binds
    abstract MessageContract.Model bindMessageModel(MessageModel model);
}