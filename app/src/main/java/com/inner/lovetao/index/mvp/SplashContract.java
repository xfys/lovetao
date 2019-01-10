package com.inner.lovetao.index.mvp;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

/**
 * desc:
 * Created by xcz
 * on 2019/1/10.
 */
public interface SplashContract {
    interface View extends IView {
        void toGuild();

        void toMain();
    }

    interface Model extends IModel {

    }
}
