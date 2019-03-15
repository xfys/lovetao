package com.inner.lovetao.loginregister.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.inner.lovetao.config.UserInfo;
import com.inner.lovetao.core.TaoResponse;
import com.inner.lovetao.loginregister.UserApi;
import com.inner.lovetao.loginregister.mvp.contract.BindPhoneActivityContract;
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
public class BindPhoneActivityModel extends BaseModel implements BindPhoneActivityContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public BindPhoneActivityModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<TaoResponse> getPhoneCode(String phone) {
        return mRepositoryManager.obtainRetrofitService(UserApi.class).getPhoneCode(phone);
    }

    @Override
    public Observable<TaoResponse<UserInfo>> bindPhone(String phone, String verifyCode, String InvitationCode, String nike, String imgUrl, String openId, String sid) {
        return mRepositoryManager.obtainRetrofitService(UserApi.class).bindPhone(phone, verifyCode, InvitationCode, imgUrl, nike, openId, sid);
    }
}