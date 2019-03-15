package com.inner.lovetao.tab.di.module;

import android.app.Dialog;

import com.inner.lovetao.tab.contract.MineFragmentContract;
import com.inner.lovetao.tab.mvp.model.MineFragmentModel;
import com.inner.lovetao.weight.dialog.ProgresDialog;
import com.jess.arms.di.scope.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * desc:
 * Created by xcz
 * on 2019/3/5.
 */
@Module
public abstract class MineFragmentModule {
    @Binds
    abstract MineFragmentContract.Model bindModel(MineFragmentModel model);

    @ActivityScope
    @Provides
    static Dialog provideDialog(MineFragmentContract.View view) {
        return new ProgresDialog(view.getActivity());
    }

}
