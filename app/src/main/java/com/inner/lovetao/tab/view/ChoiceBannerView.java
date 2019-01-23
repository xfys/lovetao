package com.inner.lovetao.tab.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.inner.lovetao.R;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.http.imageloader.glide.GlideImageLoaderStrategy;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.List;

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
    private List<String> imgs = new ArrayList<String>() {{
        add("https://img.g-banker.com/data/test/banner/M2LYIB3A_20180131144354.png");
        add("https://img.g-banker.com/data/online/banner/TH934ALJ_20181212172151.jpg");
        add("http://pic27.nipic.com/20130331/9252150_140012316365_2.jpg");
        add("http://p2.so.qhimgs1.com/bdr/200_200_/t01b53912c4720a714e.jpg");
        add("https://img.g-banker.com/data/test/banner/MCZHQDCE_20190115231806.jpg");
        add("https://img.g-banker.com/data/test/banner/6IGMAEUF_20190115231825.jpg");
    }};
    private List<String> tips = new ArrayList<String>() {{
        add("0");
        add("1");
        add("2");
        add("3");
        add("4");
        add("5");

    }};
    private GlideImageLoaderStrategy strategy;

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
        strategy = new GlideImageLoaderStrategy();
    }

    public void setData() {
        banner.setAdapter(this);
        banner.setDelegate(this);
        banner.setData(R.layout.choice_banner_item_view, imgs, tips);
        banner.getViewPager().setPageMargin(-ArmsUtils.dip2px(context, 33));
    }

    @Override
    public void fillBannerItem(BGABanner banner, View itemView, @Nullable String model, int position) {
        strategy.loadImage(context, ImageConfigImpl.builder()
                .imageRadius(ArmsUtils.dip2px(context, 6))
                .url(model)
                .imageView(itemView.findViewById(R.id.iv_banner))
                .cacheStrategy(0)
                .isCenterCrop(false)
                .build());
    }

    @Override
    public void onBannerItemClick(BGABanner banner, View itemView, @Nullable String model, int position) {
        ArmsUtils.makeText(context, "点击了" + position);
    }
}
