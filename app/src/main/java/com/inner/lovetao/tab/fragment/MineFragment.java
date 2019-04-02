package com.inner.lovetao.tab.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.inner.lovetao.R;
import com.inner.lovetao.config.ArouterConfig;
import com.inner.lovetao.config.BannerType;
import com.inner.lovetao.config.UserInfo;
import com.inner.lovetao.config.UserInstance;
import com.inner.lovetao.tab.bean.BannerBean;
import com.inner.lovetao.tab.contract.MineFragmentContract;
import com.inner.lovetao.tab.di.component.DaggerMineFragmentComponent;
import com.inner.lovetao.tab.mvp.MineFragmentPresenter;
import com.inner.lovetao.weight.PullToRefreshDefaultHeader;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.config.CommonImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * desc:我的fragment
 * Created by xcz
 * on 2019/1/15.
 */
public class MineFragment extends BaseFragment<MineFragmentPresenter> implements MineFragmentContract.View {

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
    @BindView(R.id.iv_ads)
    ImageView ivAds;
    private UserInfo userInfo;
    private BannerBean bannerBean;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerMineFragmentComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initPullToRefresh();
        mPresenter.getBanner(BannerType.MINE_PROMOTION.getType());
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


    @OnClick({R.id.iv_setting, R.id.ll_mine_earnings, R.id.ll_mine_order, R.id.ll_mine_collect, R.id.ll_mine_getVolume, R.id.ll_mine_disciple, R.id.ll_invite_money, R.id.ll_call_service, R.id.ll_suggest, R.id.ll_praise, R.id.ll_about_us, R.id.iv_photo, R.id.iv_ads})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_setting:
                ARouter.getInstance().build(ArouterConfig.AC_SETTING).navigation(mContext);
                break;
            case R.id.ll_mine_earnings:
                ARouter.getInstance().build(ArouterConfig.AC_MINE_EARNING).navigation(mContext);
                break;
            case R.id.ll_mine_order:
                ARouter.getInstance().build(ArouterConfig.AC_MY_ORDER).navigation(mContext);
                break;
            case R.id.ll_mine_collect:
                break;
            case R.id.ll_mine_getVolume:
                break;
            case R.id.ll_mine_disciple:

                break;
            case R.id.ll_invite_money:
                if (UserInstance.getInstance().isLogin(mContext)) {
                    ARouter.getInstance().build(ArouterConfig.AC_WEBVIEW).withString(ArouterConfig.ParamKey.STR_WEBVIEW_URL, "http://www.17itao.com/h5/#/invite/index").navigation(mContext);
                } else {
                    ARouter.getInstance().build(ArouterConfig.AC_TB_AUTH).navigation(mContext);
                }
                break;
            case R.id.ll_call_service:
                ARouter.getInstance().build(ArouterConfig.AC_CONTACT_SERVICE).navigation(mContext);
                break;
            case R.id.ll_suggest:
                ARouter.getInstance().build(ArouterConfig.AC_SUGGEST).navigation(mContext);
                break;
            case R.id.ll_praise:
                showMessage("敬请期待");
//                ARouter.getInstance().build(ArouterConfig.AC_WEBVIEW).withString(ArouterConfig.ParamKey.STR_WEBVIEW_URL, "http://140.143.8.96:8080/h5/#/invite/index").navigation(mContext);
                break;
            case R.id.ll_about_us:
                ARouter.getInstance().build(ArouterConfig.AC_ABOUT_US).navigation(mContext);
                break;
            case R.id.iv_photo:
                if (!UserInstance.getInstance().isLogin(mContext)) {
                    ARouter.getInstance().build(ArouterConfig.AC_TB_AUTH).navigation(mContext);
                }
                break;
            case R.id.iv_ads:
                if (bannerBean != null && !TextUtils.isEmpty(bannerBean.getContentUrl())) {
                    if (bannerBean.getLoginState() == 1) {
                        if (UserInstance.getInstance().isLogin(mContext)) {
                            ARouter.getInstance().build(ArouterConfig.AC_WEBVIEW).withString(ArouterConfig.ParamKey.STR_WEBVIEW_URL, bannerBean.getContentUrl()).navigation(mContext);
                        } else {
                            ARouter.getInstance().build(ArouterConfig.AC_TB_AUTH).navigation(mContext);
                        }
                    } else {
                        ARouter.getInstance().build(ArouterConfig.AC_WEBVIEW).withString(ArouterConfig.ParamKey.STR_WEBVIEW_URL, bannerBean.getContentUrl()).navigation(mContext);
                    }
                }
                break;
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        userInfo = UserInstance.getInstance().getUserInfo(mContext);
        setUserInfo();
    }

    private void setUserInfo() {
        if (userInfo != null && !TextUtils.isEmpty(userInfo.getNick()) && !TextUtils.isEmpty(userInfo.getHeadPicUrl())) {
            tvMineName.setText(userInfo.getNick());
            mPresenter.getmImageLoader().loadImage(mContext,
                    CommonImageConfigImpl
                            .builder()
                            .isCropCenter(true)
                            .isCropCircle(true)
                            .url(userInfo.getHeadPicUrl())
                            .imageView(ivPhoto)
                            .build());
        } else {
            tvMineName.setText("未登录");
            ivPhoto.setImageResource(R.mipmap.icon_mine_photo);
        }


    }

    @Override
    public void getBannerDataSu(List<BannerBean> bannerBeanList) {
        if (bannerBeanList != null && bannerBeanList.size() > 0) {
            bannerBean = bannerBeanList.get(0);
            if (bannerBean != null) {
                mPresenter.getmImageLoader().loadImage(mContext,
                        CommonImageConfigImpl
                                .builder()
                                .imageRadius(ArmsUtils.dip2px(mContext, 6))
                                .url(bannerBean.getImgUrl())
                                .isCropCenter(false)
                                .imageView(ivAds)
                                .build());
            }
        }

    }

    @Override
    public void showMessage(@NonNull String message) {
        ArmsUtils.makeText(mContext, message);
    }
}
