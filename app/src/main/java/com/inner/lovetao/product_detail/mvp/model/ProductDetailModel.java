package com.inner.lovetao.product_detail.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.inner.lovetao.core.TaoResponse;
import com.inner.lovetao.product_detail.DetailApi;
import com.inner.lovetao.product_detail.bean.ProductDetailBean;
import com.inner.lovetao.product_detail.mvp.contract.ProductDetailContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * Desc:
 * Created by xcz
 * on 2019/03/09
 */
@ActivityScope
public class ProductDetailModel extends BaseModel implements ProductDetailContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public ProductDetailModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }


    @Override
    public Observable<TaoResponse<ProductDetailBean>> getProductDetail(String numLid) {
        return mRepositoryManager.obtainRetrofitService(DetailApi.class).getProductDetail(numLid);
    }
}