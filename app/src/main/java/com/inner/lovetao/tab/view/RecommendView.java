package com.inner.lovetao.tab.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.inner.lovetao.R;
import com.inner.lovetao.config.ArouterConfig;
import com.inner.lovetao.config.UserInstance;
import com.inner.lovetao.tab.bean.BannerBean;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.config.CommonImageConfigImpl;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.utils.ArmsUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * desc:精选五个item
 * Created by xcz
 * on 2019/1/22.
 */
public class RecommendView extends LinearLayout {
    private Context context;
    private List<BannerBean> data;
    @BindView(R.id.tv_fourth)
    TextView tvFour;
    @BindView(R.id.tv_five)
    TextView tvFive;
    @BindView(R.id.iv_fourth)
    ImageView ivFour;
    @BindView(R.id.iv_five)
    ImageView ivFive;
    private AppComponent mAppComponent;
    private ImageLoader mImageLoader;

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
        mAppComponent = ArmsUtils.obtainAppComponentFromContext(context);
        mImageLoader = mAppComponent.imageLoader();
    }

    public void setData(List<BannerBean> data) {
        if (data != null && data.size() >= 2) {
            this.data = data;
            if (!TextUtils.isEmpty(data.get(0).getTitle())) {
                tvFour.setText(data.get(0).getTitle());
            }
            if (!TextUtils.isEmpty(data.get(1).getTitle())) {
                tvFive.setText(data.get(1).getTitle());
            }
            if (!TextUtils.isEmpty(data.get(0).getImgUrl())) {
                mImageLoader.loadImage(context,
                        CommonImageConfigImpl
                                .builder()
                                .url(data.get(0).getImgUrl())
                                .imageView(ivFour)
                                .build());
            }
            if (!TextUtils.isEmpty(data.get(1).getImgUrl())) {
                mImageLoader.loadImage(context,
                        CommonImageConfigImpl
                                .builder()
                                .url(data.get(1).getImgUrl())
                                .imageView(ivFive)
                                .build());
            }


        }


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
                if (data != null) {
                    if (data.get(0).getLoginState() == 0) {
                        if (UserInstance.getInstance().isLogin(context)) {
                            ARouter.getInstance().build(ArouterConfig.AC_WEBVIEW).withString(ArouterConfig.ParamKey.STR_WEBVIEW_URL, data.get(0).getContentUrl()).navigation(context);
                        } else {
                            ARouter.getInstance().build(ArouterConfig.AC_TB_AUTH).navigation(context);
                        }
                    } else {
                        ARouter.getInstance().build(ArouterConfig.AC_WEBVIEW).withString(ArouterConfig.ParamKey.STR_WEBVIEW_URL, data.get(0).getContentUrl()).navigation(context);
                    }

                }
                break;
            case R.id.ll_five:
                if (data != null) {
                    if (data.get(1).getLoginState() == 0) {
                        if (UserInstance.getInstance().isLogin(context)) {
                            ARouter.getInstance().build(ArouterConfig.AC_WEBVIEW).withString(ArouterConfig.ParamKey.STR_WEBVIEW_URL, data.get(1).getContentUrl()).navigation(context);
                        } else {
                            ARouter.getInstance().build(ArouterConfig.AC_TB_AUTH).navigation(context);
                        }
                    } else {
                        ARouter.getInstance().build(ArouterConfig.AC_WEBVIEW).withString(ArouterConfig.ParamKey.STR_WEBVIEW_URL, data.get(1).getContentUrl()).navigation(context);
                    }
                }
                break;
        }

    }

}
