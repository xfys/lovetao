package com.inner.lovetao.webview.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.inner.lovetao.webview.mvp.contract.WebContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;


/**
 * Desc:
 * Created by xcz
 * on 2019/03/06
 */
@ActivityScope
public class WebModel extends BaseModel implements WebContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public WebModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    /**
     *
     */
    public void getName(){
        System.out.println("ceshi"+"jdkjfks");
    }
}