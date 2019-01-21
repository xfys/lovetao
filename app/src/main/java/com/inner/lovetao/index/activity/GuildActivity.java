package com.inner.lovetao.index.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.inner.lovetao.R;
import com.inner.lovetao.config.ConfigInfo;
import com.inner.lovetao.home.activity.MainActivity;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.DataHelper;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * desc:引导页面
 * Created by xcz
 * on 2019/1/10.
 */
public class GuildActivity extends BaseActivity {
    @BindView(R.id.bag_banner)
    BGABanner guildeBanner;
    @BindView(R.id.v_togo)
    View vTogo;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.ac_guilde;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        guildeBanner.setData(null,
                ImageView.ScaleType.FIT_CENTER,
                R.mipmap.ic_guide_one,
                R.mipmap.ic_guide_two,
                R.mipmap.ic_guide_three);
        guildeBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 2) {
                    vTogo.setVisibility(View.VISIBLE);
                } else {
                    vTogo.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @OnClick({R.id.v_togo})
    public void onClick() {
        DataHelper.setStringSF(this, ConfigInfo.FIRST_INSTALL, ConfigInfo.FIRST_INSTALL);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
