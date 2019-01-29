package com.inner.lovetao.home.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.inner.lovetao.R;
import com.inner.lovetao.home.mvp.MainContract;
import com.inner.lovetao.home.mvp.MainPresenter;
import com.inner.lovetao.config.ArouterConfig;
import com.inner.lovetao.tab.fragment.HomePageFragment;
import com.inner.lovetao.tab.fragment.MineFragment;
import com.inner.lovetao.tab.fragment.WlfareServiceFragment;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.widget.tabview.TabView;
import com.jess.arms.widget.tabview.TabViewChild;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = ArouterConfig.AC_MAIN)
public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.Model, MainContract.View {

    private long mExitTime;
    @BindView(R.id.tab_view)
    TabView tabView;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        List<TabViewChild> tabviewList = new ArrayList<>();
        TabViewChild homePageChild = new TabViewChild(R.mipmap.icon_home_select, R.mipmap.icon_home_unselect, getString(R.string.main_homepage), new HomePageFragment());
        TabViewChild wlfareServiceChild = new TabViewChild(R.mipmap.icon_wflare_service_select, R.mipmap.icon_wflare_service_unselect, getString(R.string.main_wlfare_service), new WlfareServiceFragment());
        TabViewChild mineChild = new TabViewChild(R.mipmap.icon_mine_select, R.mipmap.icon_mine_unselect, getString(R.string.main_mine), new MineFragment());
        tabviewList.add(homePageChild);
        tabviewList.add(wlfareServiceChild);
        tabviewList.add(mineChild);
        tabView.setTabViewDefaultPosition(0);
        tabView.setTabViewChild(tabviewList, getSupportFragmentManager());
        tabView.setOnTabChildClickListener((position, currentImageIcon, currentTextView) -> {
            switch (position) {
                case 0:
                    setStatusBarMode(true);
                    break;
                case 1:
                    setStatusBarMode(true);
                    break;
                case 2:
                    setStatusBarMode(false);
                    break;
            }
        });
    }

    @Override
    public void showMessage(@NonNull String message) {
        ArmsUtils.makeText(this, message);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean useFragment() {
        return true;
    }

    @Override
    public boolean useEventBus() {
        return true;
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mExitTime > 3500) {
            showMessage(getString(R.string.str_press_again));
            mExitTime = System.currentTimeMillis();
            return;
        }
        ArmsUtils.exitApp();
    }
}
