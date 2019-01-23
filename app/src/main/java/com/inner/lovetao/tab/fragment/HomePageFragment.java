package com.inner.lovetao.tab.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.inner.lovetao.R;
import com.inner.lovetao.tab.tabfragment.ChoiceFragment;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.AndroidLiuHaiUtils;
import com.jess.arms.widget.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * desc:首页fragment
 * Created by xcz
 * on 2019/1/15.
 */
public class HomePageFragment extends BaseFragment {

    @BindView(R.id.edit_home_search)
    EditText editSearch;
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
    private String[] titles = {"精选", "男装", "女装", "童装", "玩具总动员", "电影", "视频", "剑姬"};
    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>() {{
        add(new ChoiceFragment());
        add(new MineFragment());
        add(new MineFragment());
        add(new MineFragment());
        add(new MineFragment());
        add(new MineFragment());
        add(new MineFragment());
        add(new MineFragment());
    }};

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initLiuHaiAdapter();
        tabLayout.setViewPager(viewPager, titles, getActivity(), fragmentList);
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    /**
     * 适配刘海屏，包括横竖屏切换
     */
    private void initLiuHaiAdapter() {
        if(headLayout != null){
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) headLayout.getLayoutParams();

            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                params.topMargin = 0;
            } else {
                params.topMargin = AndroidLiuHaiUtils.getNotchScreenHeight(getActivity());
            }
            headLayout.setLayoutParams(params);
        }
    }

}
