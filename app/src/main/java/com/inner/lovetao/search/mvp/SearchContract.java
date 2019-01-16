package com.inner.lovetao.search.mvp;

/*
 *
 *
 * 作 者 :YangFan
 *
 * 版 本 :1.0
 *
 * 创建日期 :2019/1/14      16:26
 *
 * 描 述 :搜索Contract
 *
 * 修订日期 :
 */

import com.inner.lovetao.beans.response.search.SearchHistoryItemBean;
import com.inner.lovetao.beans.response.search.SearchHotItemBean;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import java.util.List;

public interface SearchContract {

    interface View extends IView {
        void RefreshHistoryData(List<SearchHistoryItemBean> list);

        void RefreshHotData(List<SearchHotItemBean> list);

    }

    interface Model extends IModel {

    }
}
