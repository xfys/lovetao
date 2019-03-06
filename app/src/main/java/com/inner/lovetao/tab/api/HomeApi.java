package com.inner.lovetao.tab.api;

import com.inner.lovetao.core.TaoResponse;
import com.inner.lovetao.tab.bean.BannerBean;
import com.inner.lovetao.tab.bean.CategoryBean;
import com.inner.lovetao.tab.bean.FourAcBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * desc:
 * Created by xcz
 * on 2019/3/1.
 */
public interface HomeApi {
    /**
     * type :1 banner
     * 2 首页导航
     * 3 pop 弹窗
     * 4 我的推广
     * 5 会员福利
     * 6 更多福利
     */
    @GET("api/aitao/banners")
    Observable<TaoResponse<List<BannerBean>>> getBanners(@Query("type") int type);

    /**
     * 获取首页条目
     */
    @GET("/api/aitao/category/getAll")
    Observable<TaoResponse<List<CategoryBean>>> getCatgory();

    /**
     * 获取首页四个推荐活动数据
     */
    @GET("/api/aitao/activity/getAll")
    Observable<TaoResponse<List<FourAcBean>>> getFourAc();
}
