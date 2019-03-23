package com.inner.lovetao.loginregister.di.module;

import com.inner.lovetao.loginregister.mvp.contract.BindPhoneActivityContract;
import com.inner.lovetao.loginregister.mvp.model.BindPhoneActivityModel;

import dagger.Binds;
import dagger.Module;


/**
 * desc:
 * Created by xcz
 * on 2019/01/28
 */
@Module
public abstract class BindPhoneActivityModule {

    @Binds
    abstract BindPhoneActivityContract.Model bindBindPhoneActivityModel(BindPhoneActivityModel model);
}