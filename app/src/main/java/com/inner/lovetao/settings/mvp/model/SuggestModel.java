package com.inner.lovetao.settings.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.inner.lovetao.core.TaoResponse;
import com.inner.lovetao.loginregister.UserApi;
import com.inner.lovetao.settings.mvp.contract.SuggestContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * Desc:
 * Created by xcz
 * on 2019/02/14
 */
@ActivityScope
public class SuggestModel extends BaseModel implements SuggestContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public SuggestModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<TaoResponse<Boolean>> suggest(String content) {
        return mRepositoryManager.obtainRetrofitService(UserApi.class).suggest(content);
    }
}