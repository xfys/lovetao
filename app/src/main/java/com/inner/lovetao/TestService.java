package com.inner.lovetao;

import com.inner.lovetao.core.TaoResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * desc:
 * Created by xcz
 * on 2019/3/1.
 */
public interface TestService {
    /**
     * @return Deferred<User>
     */
    @GET("api/aitao/banners")
    Observable<TaoResponse<String>> login(@Query("type") int type);
}
