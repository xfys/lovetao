package com.inner.lovetao.tab.contract;

import android.content.Context;

import com.inner.lovetao.core.TaoResponse;
import com.inner.lovetao.tab.bean.CategoryBean;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import java.util.List;

import io.reactivex.Observable;

public interface HomeFragmentContract {
    interface View extends IView {

        void getCatgoySu(List<CategoryBean> categoryList);

        Context getActivity();
    }

    interface Model extends IModel {

        Observable<TaoResponse<List<CategoryBean>>> getCatgory();
    }
}
