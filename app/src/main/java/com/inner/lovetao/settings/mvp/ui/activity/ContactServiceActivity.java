package com.inner.lovetao.settings.mvp.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.inner.lovetao.R;
import com.inner.lovetao.config.ArouterConfig;
import com.inner.lovetao.settings.di.component.DaggerContactServiceComponent;
import com.inner.lovetao.settings.mvp.contract.ContactServiceContract;
import com.inner.lovetao.settings.mvp.presenter.ContactServicePresenter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DeviceUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

import static com.jess.arms.utils.Preconditions.checkNotNull;


/**
 * desc:联系客服
 * Created by xcz
 */
@Route(path = ArouterConfig.AC_CONTACT_SERVICE)
public class ContactServiceActivity extends BaseActivity<ContactServicePresenter> implements ContactServiceContract.View {
    @BindView(R.id.tv_erweima)
    ImageView img;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerContactServiceComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_contact_service; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
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


    @OnClick({R.id.tv_save_img, R.id.tv_copy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_save_img:
                RxPermissions rxPermissions = new RxPermissions(this);
                rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(aBoolean -> {
                    if (aBoolean) {
                        img.setDrawingCacheEnabled(true);
                        DeviceUtils.saveBmp2Gallery(ContactServiceActivity.this, Bitmap.createBitmap(img.getDrawingCache()), "aitao_quan");
                        img.setDrawingCacheEnabled(false);
                        showMessage("已保存到相册");
                    } else {
                        showMessage("请开启写入到磁盘权限");
                    }
                });

                break;
            case R.id.tv_copy:
                DeviceUtils.copyTextToBoard(this, "aitao_quan");
                showMessage("已复制到粘贴板");
                break;
        }
    }
}
