package com.inner.lovetao.tab.mvp.model;

import com.inner.lovetao.core.TaoResponse;
import com.inner.lovetao.tab.api.HomeApi;
import com.inner.lovetao.tab.bean.BannerBean;
import com.inner.lovetao.tab.bean.FourAcBean;
import com.inner.lovetao.tab.bean.ProductItemBean;
import com.inner.lovetao.tab.contract.ChoicFragmentContract;
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
public class ChoiceFragmentModel extends BaseModel implements ChoicFragmentContract.Model {
    @Inject
    public ChoiceFragmentModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }


    @Override
    public Observable<TaoResponse<List<BannerBean>>> getBannerData(int type) {
        return mRepositoryManager.obtainRetrofitService(HomeApi.class).getBanners(type);
    }

    @Override
    public Observable<TaoResponse<List<FourAcBean>>> getFourAcData() {
        return mRepositoryManager.obtainRetrofitService(HomeApi.class).getFourAc();
    }

    @Override
    public Observable<TaoResponse<List<ProductItemBean>>> getJingPinAcData(int pageNum, int pageSize, int activityId) {
        return mRepositoryManager.obtainRetrofitService(HomeApi.class).getActivityList(pageNum,pageSize,activityId);
    }
}
