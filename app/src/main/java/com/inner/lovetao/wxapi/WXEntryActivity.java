package com.inner.lovetao.wxapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.jess.arms.utils.ArmsUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/**
 * desc:微信分享回调
 * Created by xcz
 * on 2019/2/26.
 */
public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {


    @Override
    public void onReq(BaseReq baseReq) {
    }

    @Override
    public void onResp(BaseResp baseResp) {

        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                // 成功
                int type = baseResp.getType();
                if (type == ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX) {
                    // 分享
                    ArmsUtils.makeText(this, "分享成功");
                    break;
                }

                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                // 用户取消
                ArmsUtils.makeText(this, "分享取消");
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                // 用户拒绝
                ArmsUtils.makeText(this, "用户取消");
                break;
        }

        finish();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        finish();
    }
}
