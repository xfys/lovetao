package com.inner.lovetao.tab.mvp.model;

import com.inner.lovetao.tab.contract.WlfareServiceFragmentContract;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;

import javax.inject.Inject;

/**
 * desc:
 * Created by xcz
 * on 2019/3/5.
 */
@ActivityScope
public class WlfareServiceFragmentModel extends BaseModel implements WlfareServiceFragmentContract.Model {
    @Inject
    public WlfareServiceFragmentModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }


}
