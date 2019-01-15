package com.inner.lovetao.channel.model;

import android.app.Application;

import com.google.gson.Gson;
import com.inner.lovetao.channel.contract.ShelvesContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;


/**
 * Description:
 * <p>
 * Created by feihaokui on 01/14/2019 17:37
 */
@ActivityScope
public class ShelvesModel extends BaseModel implements ShelvesContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public ShelvesModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}