package com.inner.lovetao.tab.fragment;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.inner.lovetao.R;
import com.inner.lovetao.config.ArouterConfig;
import com.inner.lovetao.search.mvp.ui.activity.SearchActivity;
import com.inner.lovetao.tab.bean.CategoryBean;
import com.inner.lovetao.tab.contract.HomeFragmentContract;
import com.inner.lovetao.tab.di.component.DaggerHomeFragmentComponent;
import com.inner.lovetao.tab.mvp.HomeFragmentPresenter;
import com.inner.lovetao.tab.tabfragment.CategoryFragment;
import com.inner.lovetao.tab.tabfragment.ChoiceFragment;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.AndroidLiuHaiUtils;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.widget.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * desc:首页fragment
 * Created by xcz
 * on 2019/1/15.
 */
public class HomePageFragment extends BaseFragment<HomeFragmentPresenter> implements HomeFragmentContract.View {


    @BindView(R.id.iv_msg)
    ImageView ivMsg;
    @BindView(R.id.v_msg_have)
    View vMsgHave;
    @BindView(R.id.tab_layout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.head)
    RelativeLayout headLayout;
    private String[] array;
    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
    private List<String> list = new ArrayList<>();

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        //如找不到该类,请编译一下项目
        DaggerHomeFragmentComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
        ;
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        vMsgHave.setVisibility(View.GONE);
        initLiuHaiAdapter();
        mPresenter.getCatgory();

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @OnClick({R.id.edit_home_search, R.id.iv_msg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edit_home_search:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
            case R.id.iv_msg:
                ARouter.getInstance().build(ArouterConfig.AC_MESSAGE).navigation(mContext);
                break;
        }

    }

    private void initLiuHaiAdapter() {
        if (headLayout != null) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) headLayout.getLayoutParams();

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                params.topMargin = 0;
            } else {
                params.topMargin = AndroidLiuHaiUtils.getNotchScreenHeight(getActivity());
            }
            headLayout.setLayoutParams(params);
        }
    }

    @Override
    public void getCatgoySu(List<CategoryBean> categoryList) {
        list.clear();
        fragmentList.clear();
        if (categoryList != null) {
            for (CategoryBean bean : categoryList) {
                if ("精选".equals(bean.getName())) {
                    fragmentList.add(new ChoiceFragment());
                    list.add(bean.getName());
                    continue;
                }
                CategoryFragment categoryFragment = new CategoryFragment();
                categoryFragment.setData(bean);
                list.add(bean.getName());
                fragmentList.add(categoryFragment);
            }
        }
        array = list.toArray(new String[list.size()]);
        if (array != null && fragmentList != null) {
            tabLayout.setViewPager(viewPager, array, getActivity(), fragmentList);
        }
    }

    @Override
    public void showMessage(@NonNull String message) {
        ArmsUtils.makeText(getContext(), message);
    }
}
