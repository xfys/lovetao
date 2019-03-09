package com.inner.lovetao.tab.contract;

import android.content.Context;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

public interface WlfareServiceFragmentContract {
    interface View extends IView {


        Context getActivity();
    }

    interface Model extends IModel {

    }
}
