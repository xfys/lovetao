package com.inner.lovetao.tab.mvp.model;

import com.inner.lovetao.core.TaoResponse;
import com.inner.lovetao.tab.api.HomeApi;
import com.inner.lovetao.tab.bean.ProductItemBean;
import com.inner.lovetao.tab.contract.CategoryFragmentContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * desc:
 * Created by xcz
 * on 2019/3/5.
 */
@ActivityScope
public class CategoryFragmentModel extends BaseModel implements CategoryFragmentContract.Model {
    @Inject
    public CategoryFragmentModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }


    @Override
    public Observable<TaoResponse<List<ProductItemBean>>> getProductData(int pageNum, int pageSize, int activityId) {
        return mRepositoryManager.obtainRetrofitService(HomeApi.class).getActivityList(pageNum, pageSize, activityId);
    }
}
