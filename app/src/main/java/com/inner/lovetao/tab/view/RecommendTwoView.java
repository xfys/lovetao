package com.inner.lovetao.tab.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.inner.lovetao.R;
import com.inner.lovetao.tab.bean.FourAcBean;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.config.CommonImageConfigImpl;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.utils.ArmsUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * desc:精选四张图片
 * Created by xcz
 * on 2019/1/22.
 */
public class RecommendTwoView extends LinearLayout {
    private Context context;
    @BindView(R.id.iv_recommend_first)
    ImageView ivFirst;
    @BindView(R.id.iv_recommend_second)
    ImageView ivSecond;
    @BindView(R.id.iv_recommend_third)
    ImageView ivThird;
    @BindView(R.id.iv_recommend_fourth)
    ImageView ivFourth;
    private AppComponent mAppComponent;
    private ImageLoader mImageLoader;
    private List<FourAcBean> data;

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
        mAppComponent = ArmsUtils.obtainAppComponentFromContext(context);
        mImageLoader = mAppComponent.imageLoader();
    }


    @OnClick({R.id.iv_recommend_first, R.id.iv_recommend_second, R.id.iv_recommend_third, R.id.iv_recommend_fourth})
    public void onClick(View view) {
        if (data != null && data.size() >= 4) {
            switch (view.getId()) {
                case R.id.iv_recommend_first:
                    ArmsUtils.makeText(context, data.get(0).getName());
                    break;
                case R.id.iv_recommend_second:
                    ArmsUtils.makeText(context, data.get(1).getName());
                    break;
                case R.id.iv_recommend_third:
                    ArmsUtils.makeText(context, data.get(2).getName());
                    break;
                case R.id.iv_recommend_fourth:
                    ArmsUtils.makeText(context, data.get(3).getName());
                    break;
            }
        }
    }

    public void setData(List<FourAcBean> data) {
        this.data = data;
        if (data.size() >= 4) {
            mImageLoader.loadImage(context,
                    CommonImageConfigImpl
                            .builder()
                            .imageRadius(ArmsUtils.dip2px(context, 2))
                            .url(data.get(0).getPicUrl())
                            .isCropCenter(false)
                            .imageView(ivFirst)
                            .build());
            mImageLoader.loadImage(context,
                    CommonImageConfigImpl
                            .builder()
                            .imageRadius(ArmsUtils.dip2px(context, 2))
                            .url(data.get(1).getPicUrl())
                            .isCropCenter(false)
                            .imageView(ivSecond)
                            .build());
            mImageLoader.loadImage(context,
                    CommonImageConfigImpl
                            .builder()
                            .imageRadius(ArmsUtils.dip2px(context, 2))
                            .url(data.get(2).getPicUrl())
                            .isCropCenter(false)
                            .imageView(ivThird)
                            .build());
            mImageLoader.loadImage(context,
                    CommonImageConfigImpl
                            .builder()
                            .imageRadius(ArmsUtils.dip2px(context, 2))
                            .url(data.get(3).getPicUrl())
                            .isCropCenter(false)
                            .imageView(ivFourth)
                            .build());
        }

    }
}
