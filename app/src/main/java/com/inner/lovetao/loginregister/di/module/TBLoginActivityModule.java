package com.inner.lovetao.loginregister.di.module;

import android.app.Dialog;

import com.inner.lovetao.loginregister.mvp.contract.TBLoginActivityContract;
import com.inner.lovetao.loginregister.mvp.model.TBLoginActivityModel;
import com.inner.lovetao.weight.dialog.ProgresDialog;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;


/**
 * desc:
 * Created by xcz
 * on 2019/01/28
 */
@Module
public abstract class TBLoginActivityModule {
    @Binds
    abstract TBLoginActivityContract.Model bindModel(TBLoginActivityModel model);

    @ActivityScope
    @Provides
    static Dialog provideDialog(TBLoginActivityContract.View view) {
        return new ProgresDialog(view.getActivity());
    }
}