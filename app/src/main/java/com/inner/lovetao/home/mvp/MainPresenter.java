package com.inner.lovetao.home.mvp;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.jess.arms.mvp.BasePresenter;

/**
 * desc:
 * Created by xcz
 * on 2019/1/10.
 */
public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View> {
    public MainPresenter(MainContract.Model model, MainContract.View view) {
        super(model, view);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {

    }
}
