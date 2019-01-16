package com.inner.lovetao.channel.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.inner.lovetao.R;
import com.inner.lovetao.channel.adapter.ShelvesAdapterViewPager;
import com.inner.lovetao.channel.contract.ShelvesContract;
import com.inner.lovetao.channel.presenter.ShelvesPresenter;
import com.inner.lovetao.channel.ui.fragment.GoodsFragment;
import com.inner.lovetao.di.component.DaggerShelvesComponent;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * Description:商品列表
 * <p>
 * Created by feihaokui on 01/14/2019 17:37
 */
public class ShelvesActivity extends BaseActivity<ShelvesPresenter> implements ShelvesContract.View, ShelvesContract.Model {

    @BindView(R.id.my_toolbar_title)
    TextView myToolbarTitle;
    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;
    @BindView(R.id.ac_shelves_tabLayout)
    TabLayout acShelvesTabLayout;
    @BindView(R.id.common_recommend)
    TextView commonRecommend;
    @BindView(R.id.common_newest)
    TextView commonNewest;
    @BindView(R.id.common_sales)
    TextView commonSales;
    @BindView(R.id.common_price)
    TextView commonPrice;
    @BindView(R.id.ac_shelves_viewpager)
    ViewPager acShelvesViewpager;

    private String[] titles = {"推荐","女装","男装","零食","日用","母婴","数码","美女"};
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerShelvesComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_shelves;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        myToolbarTitle.setText(R.string.shelves_title);
        List<Fragment> fragments = new ArrayList<>();


        for (int i = 0; i < titles.length; i++) {
            fragments.add(new GoodsFragment());
            acShelvesTabLayout.addTab(acShelvesTabLayout.newTab());
        }

        acShelvesTabLayout.setTabMode(TabLayout.MODE_FIXED);
        acShelvesTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        acShelvesTabLayout.setupWithViewPager(acShelvesViewpager,false);//将TabLayout和ViewPager关联起来。
        ShelvesAdapterViewPager mAdapter = new ShelvesAdapterViewPager(getSupportFragmentManager(),fragments);
        acShelvesViewpager.setAdapter(mAdapter);
        acShelvesTabLayout.setTabsFromPagerAdapter(mAdapter);

        for (int i=0;i<titles.length;i++){
            acShelvesTabLayout.getTabAt(i).setText(titles[i]);//添加tab选项
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.common_recommend, R.id.common_newest, R.id.common_sales, R.id.common_price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_recommend:
                break;
            case R.id.common_newest:
                break;
            case R.id.common_sales:
                break;
            case R.id.common_price:
                break;
        }
    }
}
