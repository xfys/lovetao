package com.inner.lovetao.tab.api;

import com.inner.lovetao.core.TaoResponse;
import com.inner.lovetao.tab.bean.BannerBean;

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
}
