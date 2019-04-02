package com.inner.lovetao.webview.js_bean;

import java.io.Serializable;

/**
 * desc:
 * Created by xcz
 * on 2019/3/24.
 */
public class ShareBean implements Serializable {

    /**
     * shareUrl :
     * title : 题目
     * content : 内容
     */

    private String shareUrl;
    private String title;
    private String content;
    private String shareImg = "http://pnw1j1d9g.bkt.clouddn.com/108-108.png";

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getShareImg() {
        return shareImg;
    }

    public void setShareImg(String shareImg) {
        this.shareImg = shareImg;
    }
}
