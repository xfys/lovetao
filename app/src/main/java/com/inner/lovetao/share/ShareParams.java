package com.inner.lovetao.share;

import android.graphics.Bitmap;

/**
 * desc:分享参数
 * Created by xcz
 * on 2019/2/26.
 */
public class ShareParams {

    public ShareParams(int mShareType, String mTitle, String mText, String mLinkUrl) {
        this.mShareType = mShareType;
        this.mTitle = mTitle;
        this.mText = mText;
        this.mLinkUrl = mLinkUrl;
    }

    private int mShareType;

    private String mTitle = "";
    private String mText = "";
    private String mLinkUrl = "";
    private String mNetworkImageUrl = "";

    private Bitmap mLocalBitmap;

    /**
     * 分享类型
     */
    public void setShareType(int shareType) {
        this.mShareType = shareType;
    }

    /**
     * 分享标题
     */
    public void setTitle(String title) {
        this.mTitle = title;
    }

    /**
     * 分享文本  分享到新浪微博时一定要设置text
     */
    public void setText(String text) {
        this.mText = text;
    }

    /**
     * 分享链接
     */
    public void setLinkUrl(String urlString) {
        this.mLinkUrl = urlString;
    }

    /**
     * 分享网络图片
     */
    public void setNetworkImage(String url) {
        mNetworkImageUrl = url;
    }

    /**
     * 分享本地图片
     */
    public void setLocalBitmap(Bitmap shareBitmap) {
        mLocalBitmap = shareBitmap;
    }

    public int getShareType() {
        return mShareType;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getText() {
        return mText;
    }

    public String getLinkUrl() {
        return mLinkUrl;
    }

    public Bitmap getLocalBitmap() {
        return mLocalBitmap;
    }

    public String getNetworkImageUrl() {
        return mNetworkImageUrl;
    }
}
