package com.inner.lovetao.tab.contract;

import android.content.Context;

import com.inner.lovetao.core.TaoResponse;
import com.inner.lovetao.tab.bean.BannerBean;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import java.util.List;

import io.reactivex.Observable;

public interface WlfareServiceFragmentContract {
    interface View extends IView {
        void getBannerDataSu(int type, List<BannerBean> bannerBeanList);

        Context getActivity();
    }

    interface Model extends IModel {
        Observable<TaoResponse<List<BannerBean>>> getBannerData(int type);
    }
}
