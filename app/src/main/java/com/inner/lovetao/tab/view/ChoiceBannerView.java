package com.inner.lovetao.tab.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.inner.lovetao.R;
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
public class ChoiceBannerView extends LinearLayout implements BGABanner.Delegate<ImageView, String>, BGABanner.Adapter<ImageView, String> {
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
    }

    public void setData() {
        banner.setAdapter(this);
        banner.setData(imgs, tips);
    }

    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, @Nullable String model, int position) {
        Glide.with(itemView.getContext())
                .load(model)
                .apply(new RequestOptions().dontAnimate().centerCrop())
                .into(itemView);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, @Nullable String model, int position) {
        ArmsUtils.makeText(context, "点击了" + position);
    }
}
