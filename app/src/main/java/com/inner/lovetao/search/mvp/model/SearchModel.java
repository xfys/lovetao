package com.inner.lovetao.search.mvp.model;

/*
 *
 *
 * 作 者 :YangFan
 *
 * 版 本 :1.0
 *
 * 创建日期 :2019/1/28      18:11
 *
 * 描 述 :
 *
 * 修订日期 :
 */

import android.app.Application;

import com.google.gson.Gson;
import com.inner.lovetao.search.mvp.contract.SearchContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

@ActivityScope
public class SearchModel extends BaseModel implements SearchContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public SearchModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}
