package com.inner.lovetao.tab.di.module;

import android.app.Dialog;

import com.inner.lovetao.tab.contract.ChoicFragmentContract;
import com.inner.lovetao.tab.mvp.model.ChoiceFragmentModel;
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
public abstract class ChoiceFragmentModule {
    @Binds
    abstract ChoicFragmentContract.Model bindModel(ChoiceFragmentModel model);

    @ActivityScope
    @Provides
    static Dialog provideDialog(ChoicFragmentContract.View view) {
        return new ProgresDialog(view.getActivity());
    }
}
