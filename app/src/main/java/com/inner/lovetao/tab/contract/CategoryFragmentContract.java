package com.inner.lovetao.tab.contract;

import android.content.Context;

import com.inner.lovetao.core.TaoResponse;
import com.inner.lovetao.tab.bean.ProductItemBean;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import java.util.List;

import io.reactivex.Observable;

public interface CategoryFragmentContract {
    interface View extends IView {
        void getProductdataSu(List<ProductItemBean> productList);

        Context getActivity();
    }

    interface Model extends IModel {
        Observable<TaoResponse<List<ProductItemBean>>> getProductData(int pageNum, int pageSize, int activityId);
    }
}
