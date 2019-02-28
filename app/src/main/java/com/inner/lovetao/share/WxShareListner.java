package com.inner.lovetao.share;

/**
 * desc:微信分享回调
 * Created by xcz
 * on 2019/2/26.
 */
public interface WxShareListner {
    void onSuccess(Object obj);

    void onFailure(int errorCode);
}
