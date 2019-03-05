package com.inner.lovetao.loginregister.mvp.contract;

import android.support.v7.app.AppCompatActivity;

import com.inner.lovetao.core.TaoResponse;
import com.inner.lovetao.tab.bean.BannerBean;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import java.util.List;

import io.reactivex.Observable;


/**
 * desc:
 * Created by xcz
 * on 2019/01/28
 */
public interface TBLoginActivityContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void getDatasu(TaoResponse<String> taoResponse);

        AppCompatActivity getActivity();
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
    }
}
