package com.inner.lovetao.channel.model;

import android.app.Application;

import com.google.gson.Gson;
import com.inner.lovetao.channel.contract.ShelvesContract;
import com.inner.lovetao.core.TaoResponse;
import com.inner.lovetao.tab.api.HomeApi;
import com.inner.lovetao.tab.bean.ProductItemBean;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


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


    @Override
    public Observable<TaoResponse<List<ProductItemBean>>> getTodaySale(int pageNum, int pageSize) {
        return mRepositoryManager.obtainRetrofitService(HomeApi.class).getTodaySale(pageNum, pageSize);
    }

    @Override
    public Observable<TaoResponse<List<ProductItemBean>>> getSale_99(int pageNum, int pageSize) {
        return mRepositoryManager.obtainRetrofitService(HomeApi.class).getSale99(pageNum, pageSize);
    }

    @Override
    public Observable<TaoResponse<List<ProductItemBean>>> getBigSale(int pageNum, int pageSize) {
        return mRepositoryManager.obtainRetrofitService(HomeApi.class).getBigSaleCoupon(pageNum, pageSize);
    }
}