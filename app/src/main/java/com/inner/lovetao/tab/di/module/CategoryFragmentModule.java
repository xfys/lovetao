package com.inner.lovetao.tab.di.module;

import android.app.Dialog;

import com.inner.lovetao.tab.contract.CategoryFragmentContract;
import com.inner.lovetao.tab.mvp.model.CategoryFragmentModel;
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
public abstract class CategoryFragmentModule {
    @Binds
    abstract CategoryFragmentContract.Model bindModel(CategoryFragmentModel model);

    @ActivityScope
    @Provides
    static Dialog provideDialog(CategoryFragmentContract.View view) {
        return new ProgresDialog(view.getActivity());
    }
}
