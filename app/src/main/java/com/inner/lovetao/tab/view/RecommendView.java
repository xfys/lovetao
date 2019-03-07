package com.inner.lovetao.tab.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.inner.lovetao.R;
import com.inner.lovetao.config.ArouterConfig;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * desc:精选五个item
 * Created by xcz
 * on 2019/1/22.
 */
public class RecommendView extends LinearLayout {
    private Context context;

    public RecommendView(Context context) {
        super(context);
        initView(context);
    }


    public RecommendView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public RecommendView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.head_choice_recommend, this, true);
        ButterKnife.bind(this);
    }

    public void setData() {

    }

    @OnClick({R.id.ll_first, R.id.ll_second, R.id.ll_third, R.id.ll_fourth, R.id.ll_five})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_first:
                ARouter.getInstance().build(ArouterConfig.AC_SHELVES).withString(ArouterConfig.ParamKey.FROM_KEY, getResources().getString(R.string.home_choice_first_desc)).navigation(context);
                break;
            case R.id.ll_second:
                ARouter.getInstance().build(ArouterConfig.AC_SHELVES).withString(ArouterConfig.ParamKey.FROM_KEY, getResources().getString(R.string.home_choice_second_desc)).navigation(context);
                break;
            case R.id.ll_third:
                ARouter.getInstance().build(ArouterConfig.AC_SHELVES).withString(ArouterConfig.ParamKey.FROM_KEY, getResources().getString(R.string.home_choice_third_desc)).navigation(context);
                break;
            case R.id.ll_fourth:
                break;
            case R.id.ll_five:
                break;
        }

    }

}
