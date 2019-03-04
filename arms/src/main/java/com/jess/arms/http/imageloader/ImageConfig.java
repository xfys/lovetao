package com.jess.arms.http.imageloader;

import android.widget.ImageView;

/**
 * desc:
 * Created by xcz
 * on 2019/3/4.
 */
public class ImageConfig {
    protected String url;
    protected ImageView imageView;
    protected int placeholder;//占位符
    protected int errorPic;//错误占位符


    public String getUrl() {
        return url;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getPlaceholder() {
        return placeholder;
    }

    public int getErrorPic() {
        return errorPic;
    }
}
