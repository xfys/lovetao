package com.inner.lovetao.tab.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.inner.lovetao.R;

import butterknife.ButterKnife;

/**
 * Description:
 * <p>
 * Created by feihaokui on 2019-01-24.
 */
public class MineLoginView extends LinearLayout {
    private Context context;

    public MineLoginView(Context context) {
        super(context);
        initView(context);
    }

    public MineLoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MineLoginView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.head_mine_login, this, true);
        ButterKnife.bind(this);
    }

}
