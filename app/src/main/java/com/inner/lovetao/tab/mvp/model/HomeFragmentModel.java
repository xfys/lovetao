package com.inner.lovetao.tab.mvp.model;

import com.inner.lovetao.core.TaoResponse;
import com.inner.lovetao.tab.api.HomeApi;
import com.inner.lovetao.tab.bean.CategoryBean;
import com.inner.lovetao.tab.contract.HomeFragmentContract;
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
public class HomeFragmentModel extends BaseModel implements HomeFragmentContract.Model {
    @Inject
    public HomeFragmentModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }


    @Override
    public Observable<TaoResponse<List<CategoryBean>>> getCatgory() {
        return mRepositoryManager.obtainRetrofitService(HomeApi.class).getCatgory();
    }
}
