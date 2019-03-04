package com.inner.lovetao.loginregister.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.inner.lovetao.TestService;
import com.inner.lovetao.loginregister.mvp.contract.TBLoginActivityContract;
import com.inner.lovetao.core.TaoResponse;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * desc:
 * Created by xcz
 * on 2019/01/28
 */
@ActivityScope
public class TBLoginActivityModel extends BaseModel implements TBLoginActivityContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public TBLoginActivityModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<TaoResponse<String>> sendTbInfo(int type) {
        return mRepositoryManager.obtainRetrofitService(TestService.class).login(type);
    }
}