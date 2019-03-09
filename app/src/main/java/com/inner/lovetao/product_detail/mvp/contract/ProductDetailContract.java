package com.inner.lovetao.product_detail.mvp.contract;

import android.app.Activity;

import com.inner.lovetao.core.TaoResponse;
import com.inner.lovetao.product_detail.bean.ProductDetailBean;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import io.reactivex.Observable;


/**
 * Desc:
 * Created by xcz
 * on 2019/03/09
 */
public interface ProductDetailContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void getProductDetailSu(ProductDetailBean detailBean);

        Activity getActivity();

    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<TaoResponse<ProductDetailBean>> getProductDetail(String numLid);
    }
}
