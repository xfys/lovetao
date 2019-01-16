package com.inner.lovetao.di.module;

import com.inner.lovetao.channel.contract.ShelvesContract;
import com.inner.lovetao.channel.model.ShelvesModel;

import dagger.Binds;
import dagger.Module;


/**
 * Description:
 * <p>
 * Created by feihaokui on 01/14/2019 17:32
 */
@Module
public abstract class ShelvesModule {

    @Binds
    abstract ShelvesContract.Model bindShelvesModel(ShelvesModel model);
}