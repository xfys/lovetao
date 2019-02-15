package com.inner.lovetao.tab.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.inner.lovetao.R;
import com.inner.lovetao.config.ArouterConfig;
import com.inner.lovetao.weight.PullToRefreshDefaultHeader;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * desc:我的fragment
 * Created by xcz
 * on 2019/1/15.
 */
public class MineFragment extends BaseFragment {

    @BindView(R.id.pull_to_refresh_layout)
    PtrFrameLayout ptrFrameLayout;
    @BindView(R.id.iv_photo)
    ImageView ivPhoto;
    @BindView(R.id.tv_mine_name)
    TextView tvMineName;
    @BindView(R.id.iv_unauthorized)
    ImageView ivUnauthorized;
    @BindView(R.id.tv_unauthorized)
    TextView tvUnauthorized;
    @BindView(R.id.ll_accumulated_earnings)
    LinearLayout mAccumulatedEarnings;
    @BindView(R.id.ll_have_withdrawal)
    LinearLayout mHaveWithdrawal;
    @BindView(R.id.ll_can_withdrawal)
    LinearLayout mCanWithdrawal;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initPullToRefresh();
    }

    private void initPullToRefresh() {
        PullToRefreshDefaultHeader header = new PullToRefreshDefaultHeader(mContext);
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //下拉刷新回调
                ptrFrameLayout.refreshComplete();
            }
        });

    }


    @Override
    public void setData(@Nullable Object data) {

    }


    @OnClick({R.id.iv_setting, R.id.ll_mine_earnings, R.id.ll_mine_order, R.id.ll_mine_collect, R.id.ll_mine_getVolume, R.id.ll_mine_disciple, R.id.ll_invite_money, R.id.ll_call_service, R.id.ll_suggest, R.id.ll_praise, R.id.ll_about_us, R.id.iv_photo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_setting:
                ARouter.getInstance().build(ArouterConfig.AC_SETTING).navigation(mContext);
                break;
            case R.id.ll_mine_earnings:
                ARouter.getInstance().build(ArouterConfig.AC_MINE_EARNING).navigation(mContext);
                break;
            case R.id.ll_mine_order:
                break;
            case R.id.ll_mine_collect:
                break;
            case R.id.ll_mine_getVolume:
                break;
            case R.id.ll_mine_disciple:
                break;
            case R.id.ll_invite_money:
                break;
            case R.id.ll_call_service:
                break;
            case R.id.ll_suggest:
                ARouter.getInstance().build(ArouterConfig.AC_SUGGEST).navigation(mContext);
                break;
            case R.id.ll_praise:
                break;
            case R.id.ll_about_us:
                ARouter.getInstance().build(ArouterConfig.AC_ABOUT_US).navigation(mContext);
                break;
            case R.id.iv_photo:
                ARouter.getInstance().build(ArouterConfig.AC_TB_AUTH).navigation(mContext);
                break;
        }
    }
}
