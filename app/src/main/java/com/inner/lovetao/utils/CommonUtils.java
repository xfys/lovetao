package com.inner.lovetao.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.jess.arms.utils.ArmsUtils;

import java.text.SimpleDateFormat;

/**
 * desc:
 * Created by xcz
 * on 2019/3/9.
 */
public class CommonUtils {
    public static String getFormatDate(String ftStr, long timeStap) {
        try {
            //"yyyy年 MM月 dd日 HH时 mm分 ss秒"
            SimpleDateFormat dateFormat = new SimpleDateFormat(ftStr);
            return dateFormat.format(timeStap);
        } catch (Exception e) {
            return "时间戳格式化错误";
        }
    }

    public static void openTb(Context context, String url) {
        if (checkPackage(context, "com.taobao.taobao")) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri uri = Uri.parse(url);// 你的商品地址
            intent.setData(uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//此句代码决定是否在APP内打开淘宝
//            intent.setClassName("com.taobao.taobao", "com.taobao.tao.detail.activity.DetailActivity");//商品详情地址
//       intent.setClassName(“com.taobao.taobao”,”com.taobao.tao.shop.router.ShopUrlRouterActivity”);  //商铺地址    没测试过
            context.startActivity(intent);
        } else {
            ArmsUtils.makeText(context, "请您先安装淘宝");

        }
    }

    public static boolean checkPackage(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            context.getPackageManager().getApplicationInfo(packageName, PackageManager
                    .GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }

    }
}
