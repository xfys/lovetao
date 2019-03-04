package com.inner.lovetao.mineassets.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.inner.lovetao.mineassets.mvp.contract.EarningsDetailContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;


/**
 * Description:
 * <p>
 * Created by feihaokui on 02/18/2019 11:15
 */
@ActivityScope
public class EarningsDetailModel extends BaseModel implements EarningsDetailContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public EarningsDetailModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}