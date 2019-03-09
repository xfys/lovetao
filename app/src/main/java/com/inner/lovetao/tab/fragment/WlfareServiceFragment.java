package com.inner.lovetao.tab.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.inner.lovetao.R;
import com.inner.lovetao.config.ArouterConfig;
import com.inner.lovetao.tab.bean.BannerBean;
import com.inner.lovetao.tab.contract.WlfareServiceFragmentContract;
import com.inner.lovetao.tab.di.component.DaggerWlfareServiceFragmentComponent;
import com.inner.lovetao.tab.mvp.WlfareServiceFragmentPresenter;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.config.CommonImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * desc:福利社fragment
 * Created by xcz
 * on 2019/1/15.
 */
public class WlfareServiceFragment extends BaseFragment<WlfareServiceFragmentPresenter> implements WlfareServiceFragmentContract.View {

    @BindView(R.id.wlfare_recy)
    RecyclerView mRecyWlfare;
    @BindView(R.id.more_recy)
    RecyclerView mRecymore;
    Unbinder unbinder;
    private GridLayoutManager layoutManager;
    private List<BannerBean> mWlDatas = new ArrayList<>();
    private List<BannerBean> mMoreDatas = new ArrayList<>();
    private CommonAdapter wldapter;
    private CommonAdapter moredapter;

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
        initRecycleView();
        mPresenter.getBannerList(5);
        mPresenter.getBannerList(6);

    }

    private void initRecycleView() {
        layoutManager = new GridLayoutManager(mContext, 4);
        mRecyWlfare.setLayoutManager(layoutManager);
        wldapter = new CommonAdapter<BannerBean>(mContext, R.layout.item_choice_banner, mWlDatas) {

            @Override
            protected void convert(ViewHolder holder, BannerBean bannerBean, int position) {
                if(bannerBean == null){
                    return;
                }
                holder.setText(R.id.tv_first,bannerBean.getTitle());
                if(mPresenter.getmImageLoader() != null && !TextUtils.isEmpty(bannerBean.getImgUrl())){
                    mPresenter.getmImageLoader().loadImage(mContext, CommonImageConfigImpl
                            .builder()
                            .imageRadius(ArmsUtils.dip2px(mContext, 2))
                            .url(bannerBean.getImgUrl())
                            .isCropCenter(false)
                            .imageView(holder.itemView.findViewById(R.id.iv_first))
                            .build());
                }
            }
        };
        wldapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if(!TextUtils.isEmpty(mWlDatas.get(position).getContentUrl())){
                    ARouter.getInstance()
                            .build(ArouterConfig.AC_WEBVIEW)
                            .withString(ArouterConfig.ParamKey.STR_WEBVIEW_URL,mWlDatas.get(position).getContentUrl())
                            .navigation(mContext);
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        mRecyWlfare.setAdapter(wldapter);


        mRecymore.setLayoutManager(new GridLayoutManager(mContext ,4));
        moredapter = new CommonAdapter<BannerBean>(mContext, R.layout.item_choice_banner, mMoreDatas) {

            @Override
            protected void convert(ViewHolder holder, BannerBean bannerBean, int position) {
                if(bannerBean == null){
                    return;
                }
                holder.setText(R.id.tv_first,bannerBean.getTitle());
                if(mPresenter.getmImageLoader() != null && !TextUtils.isEmpty(bannerBean.getImgUrl())){
                    mPresenter.getmImageLoader().loadImage(mContext, CommonImageConfigImpl
                            .builder()
                            .imageRadius(ArmsUtils.dip2px(mContext, 2))
                            .url(bannerBean.getImgUrl())
                            .isCropCenter(false)
                            .imageView(holder.itemView.findViewById(R.id.iv_first))
                            .build());
                }
            }
        };
        moredapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                if(!TextUtils.isEmpty(mMoreDatas.get(position).getContentUrl())){
                    ARouter.getInstance()
                            .build(ArouterConfig.AC_WEBVIEW)
                            .withString(ArouterConfig.ParamKey.STR_WEBVIEW_URL,mMoreDatas.get(position).getContentUrl())
                            .navigation(mContext);
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        mRecymore.setAdapter(moredapter);
    }

    @Override
    public void setData(@Nullable Object data) {

    }


    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void launchActivity(@NonNull Intent intent) {

    }


    @Override
    public void getBannerDataSu(int type, List<BannerBean> bannerBeanList) {
        if(5==type){
            mWlDatas.clear();
            mWlDatas.addAll(bannerBeanList);
            wldapter.notifyDataSetChanged();
        }else {
            mMoreDatas.clear();
            mMoreDatas.addAll(bannerBeanList);
            moredapter.notifyDataSetChanged();
        }
    }
}
