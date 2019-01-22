package com.inner.lovetao.tab.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.inner.lovetao.R;

import butterknife.ButterKnife;

/**
 * desc:精选四张图片
 * Created by xcz
 * on 2019/1/22.
 */
public class RecommendTwoView extends LinearLayout {
    private Context context;

    public RecommendTwoView(Context context) {
        super(context);
        initView(context);
    }


    public RecommendTwoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public RecommendTwoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.head_choice_recommend_two, this, true);
        ButterKnife.bind(this);
    }

    public void setData() {

    }

}
