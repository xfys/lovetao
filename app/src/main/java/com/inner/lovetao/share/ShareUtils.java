package com.inner.lovetao.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.inner.lovetao.BuildConfig;
import com.inner.lovetao.R;
import com.inner.lovetao.utils.ImageLoadUtils;
import com.jess.arms.integration.AppManager;
import com.jess.arms.utils.ArmsUtils;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * desc:分享工具
 * Created by xcz
 * on 2019/2/26.
 */
public class ShareUtils {
    public static ShareUtils getInstance() {
        return InnerHolder.INSTANCE;
    }

    private ShareUtils() {
    }

    private static class InnerHolder {
        public static final ShareUtils INSTANCE = new ShareUtils();

    }

    // IWXAPI 是第三方app和微信通信的openApi接口
    private IWXAPI api;

    public void regToWx(Context context) {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(context, BuildConfig.WX_APP_ID, true);
        // 将应用的appId注册到微信
        api.registerApp(BuildConfig.WX_APP_ID);
    }
    ///////////////// 微信分享 //////////////////////

    /**
     * 微信对话分享
     */
    public void shareWechat(ShareParams params, WxShareListner shareListener) {
        switch (params.getShareType()) {
            case ShareType.LOCAL_IMAGE:
                shareLocalImage(params, shareListener);
                break;
            case ShareType.NETWORK_IMAGE:
                shareNetworkImage(params, shareListener);
                break;
            case ShareType.LINK:
                shareLink(params, shareListener);
                break;
            default:
                shareListener.onFailure(-1);
        }
    }

    /**
     * 微信朋友圈分享
     */
    public void shareWechatMoments(ShareParams params, WxShareListner shareListener) {
        switch (params.getShareType()) {
            case ShareType.LOCAL_IMAGE:
                shareMomentsLocalImage(params, shareListener);
                break;
            case ShareType.NETWORK_IMAGE:
                shareMomentsNetworkImage(params, shareListener);
                break;
            case ShareType.LINK:
                shareMomentsLink(params, shareListener);
                break;
            default:
                shareListener.onFailure(-1);
        }
    }

    /**
     * 微信对话分享 本地图片
     */
    private void shareLocalImage(ShareParams params, WxShareListner shareListener) {
        Bitmap bmp = params.getLocalBitmap();

        if (bmp == null) {
            if (shareListener != null) {
                shareListener.onFailure(-1);
            }
            return;
        }

        wechatShareImage(shareListener, bmp);
    }

    /**
     * 微信对话分享 网络图片
     */
    private void shareNetworkImage(ShareParams params, final WxShareListner shareListener) {
        String shareImage = params.getNetworkImageUrl();
        if (TextUtils.isEmpty(shareImage)) {
            if (shareListener != null) {
                shareListener.onFailure(-1);
            }
            return;
        }
        Glide.with(AppManager.getAppManager().getCurrentActivity()).load(shareImage).addListener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                wechatShareLink(ImageLoadUtils.drawable2Bitmap(resource), params, shareListener);
                return false;
            }
        });
    }

    /**
     * 微信对话分享 在线链接
     */
    private void shareLink(final ShareParams params, final WxShareListner shareListener) {
        if (TextUtils.isEmpty(params.getLinkUrl())) {
            if (shareListener != null) {
                shareListener.onFailure(-1);
            }
            return;
        }

        // 缩略图
        String thumbUrl = params.getNetworkImageUrl();
        if (TextUtils.isEmpty(thumbUrl)) {
            wechatShareLink(getDefaultBitmap(), params, shareListener);
        } else {
            Glide.with(AppManager.getAppManager().getCurrentActivity()).load(thumbUrl).addListener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    wechatShareLink(ImageLoadUtils.drawable2Bitmap(resource), params, shareListener);
                    return false;
                }
            });
        }
    }

    /**
     * 微信朋友圈分享 本地图片
     */
    private void shareMomentsLocalImage(ShareParams params, WxShareListner shareListener) {
        Bitmap bmp = params.getLocalBitmap();

        if (bmp == null) {
            if (shareListener != null) {
                shareListener.onFailure(-1);
            }
            return;
        }

        wechatMomentsShareImage(bmp, shareListener);
    }

    /**
     * 微信朋友圈分享 网路图片
     */
    private void shareMomentsNetworkImage(ShareParams params, final WxShareListner shareListener) {
        String shareImage = params.getNetworkImageUrl();

        if (TextUtils.isEmpty(shareImage)) {
            if (shareListener != null) {
                shareListener.onFailure(-1);
            }
            return;
        }


    }

    /**
     * 微信朋友圈分享 在线链接
     */
    private void shareMomentsLink(final ShareParams params, final WxShareListner shareListener) {
        if (TextUtils.isEmpty(params.getLinkUrl())) {
            if (shareListener != null) {
                shareListener.onFailure(-1);
            }
            return;
        }

        // 缩略图
        String thumbUrl = params.getNetworkImageUrl();

        if (TextUtils.isEmpty(thumbUrl)) {
            wechatMomentsShareLink(getDefaultBitmap(), params, shareListener);
        } else {
            Glide.with(AppManager.getAppManager().getCurrentActivity()).load(thumbUrl).addListener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    wechatMomentsShareLink(ImageLoadUtils.drawable2Bitmap(resource), params, shareListener);
                    return false;
                }
            });
        }
    }

    private void wechatShareLink(Bitmap bmp, ShareParams params, WxShareListner handler) {
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        WXWebpageObject webPageObject = new WXWebpageObject();

        Bitmap thumbBit = Bitmap.createScaledBitmap(bmp, 100, 100, true);

        webPageObject.webpageUrl = params.getLinkUrl();
        WXMediaMessage msg = new WXMediaMessage(webPageObject);
        req.transaction = buildTransaction("webpage");
        msg.title = params.getTitle();
        msg.description = params.getText();
        msg.thumbData = ImageLoadUtils.parseBitmapToBytes(thumbBit);

        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;

        api.sendReq(req);
    }

    private void wechatShareImage(WxShareListner handler, Bitmap bmp) {
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        WXImageObject imageObject = new WXImageObject(bmp);
        WXMediaMessage msg = new WXMediaMessage(imageObject);

        msg.mediaObject = imageObject;

        Bitmap thumbBit = Bitmap.createScaledBitmap(bmp, 100, 100, true);
        msg.thumbData = ImageLoadUtils.parseBitmapToBytes(thumbBit);

        req.transaction = buildTransaction("img");
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;

        api.sendReq(req);
    }

    public void wechatMomentsShareLink(Bitmap bmp, ShareParams params, WxShareListner handler) {
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        WXWebpageObject webPageObject = new WXWebpageObject();
        webPageObject.webpageUrl = params.getLinkUrl();
        WXMediaMessage msg = new WXMediaMessage(webPageObject);

        Bitmap thumbBit = Bitmap.createScaledBitmap(bmp, 100, 100, true);

        req.transaction = buildTransaction("webpage");
        msg.title = params.getTitle();
        msg.description = params.getText();
        msg.thumbData = ImageLoadUtils.parseBitmapToBytes(thumbBit);
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;

        api.sendReq(req);
    }

    private void wechatMomentsShareImage(Bitmap bmp, WxShareListner handler) {
        SendMessageToWX.Req req = new SendMessageToWX.Req();

        WXImageObject imageObject = new WXImageObject(bmp);
        WXMediaMessage msg = new WXMediaMessage(imageObject);
        msg.mediaObject = imageObject;

        Bitmap thumbBit = Bitmap.createScaledBitmap(bmp, 100, 100, true);

        msg.thumbData = ImageLoadUtils.parseBitmapToBytes(thumbBit);
        req.transaction = buildTransaction("img");

        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;

        api.sendReq(req);
    }

    /**
     * 获取默认缩略图
     */
    public Bitmap getDefaultBitmap() {
        return BitmapFactory.decodeResource(ArmsUtils.getResources(AppManager.getAppManager().getCurrentActivity()), R.mipmap.ic_launcher);
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }
}
