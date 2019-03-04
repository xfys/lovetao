package com.inner.lovetao.mineassets.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.inner.lovetao.R;
import com.inner.lovetao.config.ArouterConfig;
import com.inner.lovetao.mineassets.di.component.DaggerEarningsComponent;
import com.inner.lovetao.mineassets.mvp.contract.EarningsContract;
import com.inner.lovetao.mineassets.mvp.presenter.EarningsPresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * Description:商品列表
 * <p>
 * Created by feihaokui on 01/15/2019
 */
@Route(path = ArouterConfig.AC_MINE_EARNING)
public class EarningsActivity extends BaseActivity<EarningsPresenter> implements EarningsContract.View {

    /**
     * 可提现金额
     */
    @BindView(R.id.tv_withdraw_money)
    TextView mWithdrawMoney;
    /**
     * 结算中金额
     */
    @BindView(R.id.tv_withdrawing_money)
    TextView mWithdrawingMoney;
    /**
     * 累计收益
     */
    @BindView(R.id.tv_total_money)
    TextView mTotalMoney;
    /**
     * 尚未到账
     */
    @BindView(R.id.tv_not_withdrawing)
    TextView mNotWithdrawing;
    /**
     * 累计体现
     */
    @BindView(R.id.tv_total_withdrawal)
    TextView mTotalWithdrawal;
    /**
     * 今日下单
     */
    @BindView(R.id.tv_today_order)
    TextView mTodayOrder;
    /**
     * 今日下单金额
     */
    @BindView(R.id.tv_today_money)
    TextView mTodayMoney;
    /**
     * 本月下单
     */
    @BindView(R.id.tv_mouth_order)
    TextView mMouthOrder;
    /**
     * 本月下单金额
     */
    @BindView(R.id.tv_mouth_money)
    TextView tvMouthMoney;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerEarningsComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_earnings; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
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

    @OnClick({R.id.tv_withdraw, R.id.rl_earnings_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_withdraw:
                break;
            case R.id.rl_earnings_detail:
                ARouter.getInstance().build(ArouterConfig.AC_EARNING_DETAIL).navigation(this);
                break;
        }
    }
}
