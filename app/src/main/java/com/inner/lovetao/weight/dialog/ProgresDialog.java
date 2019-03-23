package com.inner.lovetao.weight.dialog;

import android.app.Dialog;
import android.content.Context;

import com.inner.lovetao.R;

import androidx.annotation.NonNull;

/**
 * desc:
 * Created by xcz
 * on 2019/3/4.
 */
public class ProgresDialog extends Dialog {
    public ProgresDialog(@NonNull Context context) {
        super(context, R.style.public_dialog_progress);
        setContentView(R.layout.dialog_common_porgress);
        setCanceledOnTouchOutside(false);
    }
}
