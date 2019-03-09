package com.inner.lovetao.utils;

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
}
