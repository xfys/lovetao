package com.inner.lovetao.channel.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.inner.lovetao.R;
import com.inner.lovetao.channel.contract.ShelvesContract;
import com.inner.lovetao.channel.di.component.DaggerShelvesComponent;
import com.inner.lovetao.channel.presenter.ShelvesPresenter;
import com.inner.lovetao.config.ArouterConfig;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * Description:商品列表
 * <p>
 * Created by feihaokui on 01/14/2019 17:37
 */
@Route(path = ArouterConfig.AC_SHELVES)
public class ShelvesActivity extends BaseActivity<ShelvesPresenter> implements ShelvesContract.View, ShelvesContract.Model {
    @BindView(R.id.common_recommend)
    TextView commonRecommend;
    @BindView(R.id.common_newest)
    TextView commonNewest;
    @BindView(R.id.common_sales)
    TextView commonSales;
    @BindView(R.id.common_price)
    TextView commonPrice;


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
    public boolean useFragment() {
        return false;
    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    @OnClick({R.id.common_recommend, R.id.common_newest, R.id.common_sales, R.id.common_price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_recommend:
                initCommonViewColor(commonRecommend);
                break;
            case R.id.common_newest:
                initCommonViewColor(commonNewest);
                break;
            case R.id.common_sales:
                initCommonViewColor(commonSales);
                break;
            case R.id.common_price:
                initCommonViewColor(commonPrice);
                break;
        }
    }

    private void initCommonViewColor(@NonNull TextView view) {
        commonNewest.setTextColor(getResources().getColor(R.color.color_444444));
        commonPrice.setTextColor(getResources().getColor(R.color.color_444444));
        commonSales.setTextColor(getResources().getColor(R.color.color_444444));
        commonRecommend.setTextColor(getResources().getColor(R.color.color_444444));
        view.setTextColor(getResources().getColor(R.color.color_E83F5C));
    }
}
