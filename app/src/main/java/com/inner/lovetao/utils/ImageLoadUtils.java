package com.inner.lovetao.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.jess.arms.base.App;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;

import java.io.ByteArrayOutputStream;

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

    public static byte[] parseBitmapToBytes(Bitmap bitmap) {
        byte[] bytes = null;

        if (bitmap != null) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bytes = bos.toByteArray();
        }

        return bytes;
    }

    public static void clear(Context context) {
        AppComponent mAppComponent = ((App) context.getApplicationContext()).getAppComponent();
        ImageLoader mImageLoader = mAppComponent.imageLoader();
        mImageLoader.clear(mAppComponent.application(), ImageConfigImpl.builder().isClearMemory(true).build());
    }

    public static Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof NinePatchDrawable) {
            Bitmap bitmap = Bitmap
                    .createBitmap(
                            drawable.getIntrinsicWidth(),
                            drawable.getIntrinsicHeight(),
                            drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                    : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            return bitmap;
        } else {
            return null;
        }
    }
}
