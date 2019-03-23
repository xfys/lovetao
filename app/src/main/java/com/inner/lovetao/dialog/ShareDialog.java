package com.inner.lovetao.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.inner.lovetao.R;

import androidx.appcompat.app.AppCompatDialog;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * desc:分享dialog
 * Created by xcz
 * on 2019/2/28.
 */
public class ShareDialog extends AppCompatDialog {
    private final Context context;
    private ShareClick shareClick;


    public ShareDialog(Context context) {
        super(context, R.style.full_screen_dialog);
        this.context = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_share);
        ButterKnife.bind(this);
        Window window = getWindow();
        WindowManager.LayoutParams p = window.getAttributes();
        p.width = WindowManager.LayoutParams.MATCH_PARENT;
        p.height = WindowManager.LayoutParams.WRAP_CONTENT;
        p.dimAmount = 0.5f;
        window.setGravity(Gravity.BOTTOM);
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setWindowAnimations(R.style.gbanker_dialog_anim_from_bottom_to_top);
        window.setAttributes(p);
    }

    @OnClick({R.id.tv_share_wx, R.id.tv_share_wx_circle, R.id.tv_share_qq, R.id.tv_share_weibo})
    public void onClick(View view) {
        if (shareClick != null) {
            shareClick.share(view.getId());
        }
    }

    public ShareClick getShareClick() {
        return shareClick;
    }

    public void setShareClick(ShareClick shareClick) {
        this.shareClick = shareClick;
    }

    public interface ShareClick {
        void share(int id);
    }
}
