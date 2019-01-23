package com.inner.lovetao.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.jess.arms.base.App;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;

/**
 * desc: 图片加载
 * Created by xcz
 * on 2019/1/23.
 */
public class ImageLoadUtils {
    public static void showImageView(Context context, ImageView imageView, String url) {
        showImageView(context, imageView, url, 0, 0, null);
    }

    public static void showImageView(Context context, ImageView imageView, String url, int placeholder, int errorPic) {
        showImageView(context, imageView, url, placeholder, errorPic, null);
    }

    public static void showImageView(Context context, ImageView imageView, String url, BitmapTransformation bitmapTransformation) {
        showImageView(context, imageView, url, 0, 0, bitmapTransformation);
    }

    /**
     * @param context
     * @param imageView
     * @param url                  路径
     * @param placeholder          默认图片
     * @param errorPic             错误图片
     * @param bitmapTransformation 图片变形
     */
    public static void showImageView(Context context, ImageView imageView, String url, int placeholder, int errorPic, BitmapTransformation bitmapTransformation) {
        AppComponent mAppComponent = ((App) context.getApplicationContext()).getAppComponent();
        ImageLoader mImageLoader = mAppComponent.imageLoader();

        ImageConfigImpl.Builder builder = ImageConfigImpl.builder();
        builder.url(url);
        builder.imageView(imageView);
        if (bitmapTransformation != null) {
            builder.transformation(bitmapTransformation);
        }
        if (placeholder != 0) {
            builder.placeholder(placeholder);
        }
        if (errorPic != 0) {
            builder.errorPic(errorPic);
        }
        mImageLoader.loadImage(context, builder.build());
    }

    public static void clear(Context context) {
        AppComponent mAppComponent = ((App) context.getApplicationContext()).getAppComponent();
        ImageLoader mImageLoader = mAppComponent.imageLoader();
        mImageLoader.clear(mAppComponent.application(), ImageConfigImpl.builder().isClearMemory(true).build());
    }
}
