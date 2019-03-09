package com.inner.lovetao.product_detail;

import com.inner.lovetao.core.TaoResponse;
import com.inner.lovetao.product_detail.bean.ProductDetailBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * desc:产品详情api
 * Created by xcz
 * on 2019/3/9.
 */
public interface DetailApi {
    @GET("/api/aitao/itemDetail")
    Observable<TaoResponse<ProductDetailBean>> getProductDetail(@Query("numIid") String type);
}
