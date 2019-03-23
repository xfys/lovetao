package com.inner.lovetao.tab.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.inner.lovetao.R;
import com.inner.lovetao.config.ArouterConfig;
import com.inner.lovetao.config.UserInstance;
import com.inner.lovetao.tab.bean.BannerBean;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.config.CommonImageConfigImpl;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * desc:精选banner
 * Created by xcz
 * on 2019/1/22.
 */
@ActivityScope
public class ChoiceBannerView extends LinearLayout implements BGABanner.Delegate<View, String>, BGABanner.Adapter<View, String> {
    @BindView(R.id.banner_indicator)
    BGABanner banner;
    private Context context;

    private AppComponent mAppComponent;
    private ImageLoader mImageLoader;
    private List<BannerBean> datas;
    private List<String> imgs = new ArrayList<>();

    public ChoiceBannerView(Context context) {
        this(context, null);
        initView(context);
    }

    public ChoiceBannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        initView(context);
    }

    public ChoiceBannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.head_choice_banner, this, true);
        ButterKnife.bind(this);
        mAppComponent = ArmsUtils.obtainAppComponentFromContext(context);
        mImageLoader = mAppComponent.imageLoader();
    }

    public void setData(List<BannerBean> datas) {
        if (datas != null) {
            imgs.clear();
            this.datas = datas;
            for (BannerBean img : datas) {
                imgs.add(img.getImgUrl());
            }
            banner.setAdapter(this);
            banner.setDelegate(this);
            banner.setData(R.layout.choice_banner_item_view, imgs, null);
            banner.getViewPager().setPageMargin(-ArmsUtils.dip2px(context, 33));
        }
    }

    @Override
    public void fillBannerItem(BGABanner banner, View itemView, @Nullable String model, int position) {
        mImageLoader.loadImage(itemView.getContext(),
                CommonImageConfigImpl
                        .builder()
                        .imageRadius(ArmsUtils.dip2px(context, 6))
                        .url(model)
                        .isCropCenter(false)
                        .imageView(itemView.findViewById(R.id.iv_banner))
                        .build());
    }

    @Override
    public void onBannerItemClick(BGABanner banner, View itemView, @Nullable String model, int position) {
        if (datas != null) {
            if (datas.get(position).getLoginState() == 1) {
                if (UserInstance.getInstance().isLogin(context)) {
                    ARouter.getInstance().build(ArouterConfig.AC_WEBVIEW).withString(ArouterConfig.ParamKey.STR_WEBVIEW_URL, datas.get(position).getContentUrl()).navigation(context);
                } else {
                    ARouter.getInstance().build(ArouterConfig.AC_TB_AUTH).navigation(context);
                }
            } else {
                ARouter.getInstance().build(ArouterConfig.AC_WEBVIEW).withString(ArouterConfig.ParamKey.STR_WEBVIEW_URL, datas.get(position).getContentUrl()).navigation(context);
            }

        }
    }
}
