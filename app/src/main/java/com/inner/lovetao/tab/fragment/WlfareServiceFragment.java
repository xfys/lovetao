package com.inner.lovetao.tab.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inner.lovetao.R;
import com.inner.lovetao.tab.contract.WlfareServiceFragmentContract;
import com.inner.lovetao.tab.di.component.DaggerWlfareServiceFragmentComponent;
import com.inner.lovetao.tab.mvp.WlfareServiceFragmentPresenter;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;

import butterknife.OnClick;

/**
 * desc:福利社fragment
 * Created by xcz
 * on 2019/1/15.
 */
public class WlfareServiceFragment extends BaseFragment<WlfareServiceFragmentPresenter> implements WlfareServiceFragmentContract.View {

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerWlfareServiceFragmentComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wlfare_service, container, false);
        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @OnClick({R.id.tv_save_master, R.id.tv_evray_day_hb, R.id.tv_coin_travel, R.id.tv_make_money_center, R.id.tv_alipay_hb, R.id.tv_open_visa, R.id.tv_want_browring, R.id.tv_want_financing})
    public void onClick(View view) {
        switch (view.getId()) {
            //收徒邀请
            case R.id.tv_save_master:
                break;
            //每日红包
            case R.id.tv_evray_day_hb:
                break;
            //金币兑换
            case R.id.tv_coin_travel:
                break;
            //赚钱中心
            case R.id.tv_make_money_center:
                break;
            //支付宝红包
            case R.id.tv_alipay_hb:
                break;
            //办理信用卡
            case R.id.tv_open_visa:
                break;
            //我要借钱
            case R.id.tv_want_browring:
                break;
            //我要理财
            case R.id.tv_want_financing:
                break;
        }

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void launchActivity(@NonNull Intent intent) {

    }
}
