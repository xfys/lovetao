package com.inner.lovetao.channel.contract;

import android.app.Activity;

import com.inner.lovetao.core.TaoResponse;
import com.inner.lovetao.tab.bean.ProductItemBean;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import java.util.List;

import io.reactivex.Observable;


/**
 * Description:
 * <p>
 * Created by feihaokui on 01/14/2019 17:37
 */
public interface ShelvesContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void getProductListSu(List<ProductItemBean> itemBeanList);

        Activity getActivity();

    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<TaoResponse<List<ProductItemBean>>> getTodaySale(int pageNum, int pageSize);

        Observable<TaoResponse<List<ProductItemBean>>> getSale_99(int pageNum, int pageSize);

        Observable<TaoResponse<List<ProductItemBean>>> getBigSale(int pageNum, int pageSize);

        Observable<TaoResponse<List<ProductItemBean>>> getAcData(int pageNum, int pageSize, int activityId);
    }
}
