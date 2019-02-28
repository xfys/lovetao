package com.inner.lovetao.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.inner.lovetao.BuildConfig;
import com.inner.lovetao.R;
import com.inner.lovetao.dialog.ShareDialog;
import com.jess.arms.utils.ArmsUtils;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
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

    public IWXAPI getApi() {
        return api;
    }

    public void regToWx(Context context) {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        api = WXAPIFactory.createWXAPI(context, BuildConfig.WX_APP_ID, true);
        // 将应用的appId注册到微信
        api.registerApp(BuildConfig.WX_APP_ID);
    }

    ///////////////// 微信分享 //////////////////////

    /**
     * // SendMessageToWX.Req.WXSceneSession是分享到好友会话
     * // SendMessageToWX.Req.WXSceneTimeline是分享到朋友圈
     *
     * @param context
     * @param webUrl
     * @param title
     * @param content
     * @param imgUrl
     * @param type    SendMessageToWX.Req.WXSceneSession
     */
    public void shareWeb(Context context, String webUrl, String title, String content, String imgUrl, int type) {
        // 检查手机或者模拟器是否安装了微信
        if (!api.isWXAppInstalled()) {
            ArmsUtils.makeText(context, "您还没有安装微信");
            return;
        }
        // 初始化一个WXWebpageObject对象
        WXWebpageObject webpageObject = new WXWebpageObject();
        // 填写网页的url
        webpageObject.webpageUrl = webUrl;

        // 用WXWebpageObject对象初始化一个WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage(webpageObject);
        // 填写网页标题、描述、位图
        msg.title = title;
        msg.description = content;
        // 如果没有位图，可以传null，会显示默认的图片
        if (TextUtils.isEmpty(imgUrl)) {
            msg.setThumbImage(null);
        } else {
            Glide.with(context).asBitmap().load(imgUrl).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                    msg.setThumbImage(resource);
                }
            });
        }
        // 构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        // transaction用于唯一标识一个请求（可自定义）
        req.transaction = buildTransaction("webpage");
        // 上文的WXMediaMessage对象
        req.message = msg;
        // SendMessageToWX.Req.WXSceneSession是分享到好友会话
        // SendMessageToWX.Req.WXSceneTimeline是分享到朋友圈
//        req.scene = SendMessageToWX.Req.WXSceneSession;
        req.scene = type;
        // 向微信发送请求
        api.sendReq(req);
    }


    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    public void share(Context context, String webUrl, String title, String content, String imgUrl) {
        ShareDialog shareDialog = new ShareDialog(context);
        shareDialog.setShareClick(id -> {
            switch (id) {
                case R.id.tv_share_wx:
                    shareWeb(context, webUrl, title, content, imgUrl, SendMessageToWX.Req.WXSceneSession);
                    break;
                case R.id.tv_share_wx_circle:
                    shareWeb(context, webUrl, title, content, imgUrl, SendMessageToWX.Req.WXSceneTimeline);
                    break;
                case R.id.tv_share_qq:
                    break;
                case R.id.tv_share_weibo:
                    break;
            }
            shareDialog.dismiss();
        });
        shareDialog.setCancelable(true);
        shareDialog.setCanceledOnTouchOutside(true);
        shareDialog.show();

    }
}
