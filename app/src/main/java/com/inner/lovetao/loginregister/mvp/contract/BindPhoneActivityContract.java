package com.inner.lovetao.loginregister.mvp.contract;

import com.inner.lovetao.config.UserInfo;
import com.inner.lovetao.core.TaoResponse;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import io.reactivex.Observable;


/**
 * desc:
 * Created by xcz
 * on 2019/01/28
 */
public interface BindPhoneActivityContract {
    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {
        void getPhoneCodeSu();

        void bindPhoneNumSu(UserInfo userInfo);

    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,即是否使用缓存
    interface Model extends IModel {
        Observable<TaoResponse> getPhoneCode(String phone);

        Observable<TaoResponse<UserInfo>> bindPhone(String phone,
                                                    String verifyCode,
                                                    String InvitationCode,
                                                    String nike, String imgUrl, String openId, String sid);
    }
}
