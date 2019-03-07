package com.inner.lovetao.tab.api;

import com.inner.lovetao.core.TaoResponse;
import com.inner.lovetao.tab.bean.BannerBean;
import com.inner.lovetao.tab.bean.CategoryBean;
import com.inner.lovetao.tab.bean.FourAcBean;
import com.inner.lovetao.tab.bean.ProductItemBean;

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

    /**
     * 获取活动列表
     */
    @GET("/api/aitao/activity/getCoupons")
    Observable<TaoResponse<List<ProductItemBean>>> getActivityList(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("activityId") int activityId);

    /**
     * 根据条目获取商品列表
     */
    @GET("/api/aitao/coupons")
    Observable<TaoResponse<List<ProductItemBean>>> getCoupons(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("categoryId") int categoryId);

    /**
     * 获取今日特卖列表
     */
    @GET("/api/aitao/couponsByNow")
    Observable<TaoResponse<List<ProductItemBean>>> getTodaySale(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize);

    /**
     * 获取9.9特卖列表
     */
    @GET("/api/aitao/getSpecialSaleCoupon")
    Observable<TaoResponse<List<ProductItemBean>>> getSale99(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize);

    /**
     * 获取大额券列表
     */
    @GET("/api/aitao/getBigCoupon")
    Observable<TaoResponse<List<ProductItemBean>>> getBigSaleCoupon(@Query("pageNo") int pageNo, @Query("pageSize") int pageSize);
}
