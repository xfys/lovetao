package com.inner.lovetao.settings.di.module;

import com.inner.lovetao.settings.mvp.contract.SettingActivityContract;
import com.inner.lovetao.settings.mvp.model.SettingActivityModel;

import dagger.Binds;
import dagger.Module;


/**
 * desc:
 * Created by xcz
 * on 2019/01/24
 */
@Module
public abstract class SettingActivityModule {

    @Binds
    abstract SettingActivityContract.Model bindSettingActivityModel(SettingActivityModel model);
}