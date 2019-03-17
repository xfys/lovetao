package com.inner.lovetao.search.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.inner.lovetao.core.TaoResponse;
import com.inner.lovetao.search.mvp.contract.SearchResultContract;
import com.inner.lovetao.tab.api.HomeApi;
import com.inner.lovetao.tab.bean.ProductItemBean;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * desc:
 * Created by xcz
 */
@ActivityScope
public class SearchResultModel extends BaseModel implements SearchResultContract.Model {
    @Inject
    Gson mGson;
    @Inject
    Application mApplication;

    @Inject
    public SearchResultModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<TaoResponse<List<ProductItemBean>>> getSearchData(int pageNum, int pageSize, String title) {
        return mRepositoryManager.obtainRetrofitService(HomeApi.class).getSearchCoupons(pageNum, pageSize, title);
    }

    @Override
    public Observable<TaoResponse<List<ProductItemBean>>> getSearchSortData(int pageNum, int pageSize, String title, String sort) {
        return mRepositoryManager.obtainRetrofitService(HomeApi.class).getSearchCoupons(pageNum, pageSize, title,sort);
    }

    @Override
    public Observable<TaoResponse<List<ProductItemBean>>> getSearchSortsData(int pageNum, int pageSize, String title, String sortName, String sortOrder) {
        return mRepositoryManager.obtainRetrofitService(HomeApi.class).getSearchSortsCoupons(pageNum, pageSize, title,sortName,sortOrder);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }
}