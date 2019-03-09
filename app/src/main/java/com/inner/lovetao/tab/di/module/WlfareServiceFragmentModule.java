package com.inner.lovetao.tab.di.module;

import android.app.Dialog;

import com.inner.lovetao.tab.contract.WlfareServiceFragmentContract;
import com.inner.lovetao.tab.fragment.WlfareServiceFragment;
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
public abstract class WlfareServiceFragmentModule {
    @Binds
    abstract WlfareServiceFragmentContract.Model bindModel(WlfareServiceFragment model);

    @ActivityScope
    @Provides
    static Dialog provideDialog(WlfareServiceFragmentContract.View view) {
        return new ProgresDialog(view.getActivity());
    }
}
