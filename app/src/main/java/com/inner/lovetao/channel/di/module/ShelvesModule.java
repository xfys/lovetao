package com.inner.lovetao.channel.di.module;

import android.app.Dialog;

import com.inner.lovetao.channel.contract.ShelvesContract;
import com.inner.lovetao.channel.model.ShelvesModel;
import com.inner.lovetao.weight.dialog.ProgresDialog;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;


/**
 * Description:
 * <p>
 * Created by feihaokui on 01/14/2019 17:32
 */
@Module
public abstract class ShelvesModule {

    @Binds
    abstract ShelvesContract.Model bindShelvesModel(ShelvesModel model);

    @ActivityScope
    @Provides
    static Dialog provideDialog(ShelvesContract.View view) {
        return new ProgresDialog(view.getActivity());
    }
}