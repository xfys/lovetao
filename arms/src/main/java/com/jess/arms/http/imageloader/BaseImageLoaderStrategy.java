package com.jess.arms.http.imageloader;

import android.content.Context;

/**
 * desc:图片加载策略
 * Created by xcz
 * on 2019/3/4.
 */
public interface BaseImageLoaderStrategy<T extends ImageConfig> {
    /**
     * 加载图片
     *
     * @param ctx
     * @param config
     */
    void loadImage(Context ctx, T config);

    /**
     * 停止加载
     *
     * @param ctx
     * @param config
     */
    void clear(Context ctx, T config);
}
