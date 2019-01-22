package com.inner.lovetao.weight;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.inner.lovetao.R;

import java.util.Random;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

public class PullToRefreshDefaultHeader extends FrameLayout implements PtrUIHandler {

    protected ImageView loadingIv;
    protected ImageView finishIv;
    protected TextView loadingText;
    private ObjectAnimator rotateAnim;
    private String[] loadingTextList;

    public PullToRefreshDefaultHeader(Context context) {
        super(context);
        initViews(null);
    }

    public PullToRefreshDefaultHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(attrs);
    }

    public PullToRefreshDefaultHeader(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews(attrs);
    }

    protected void initViews(AttributeSet attrs) {
        View header = LayoutInflater.from(getContext()).inflate(R.layout.pull_to_refresh_default_header_layout, this);

        loadingIv = (ImageView) header.findViewById(R.id.loading_iv);
        finishIv = (ImageView) header.findViewById(R.id.finish_iv);
        loadingText = (TextView) header.findViewById(R.id.loading_text);

        rotateAnim = ObjectAnimator.ofFloat(loadingIv, "rotation", 0f, 360f);
        rotateAnim.setDuration(1500);
        rotateAnim.setRepeatCount(-1);

    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        loadingIv.setVisibility(View.VISIBLE);
        finishIv.setVisibility(View.GONE);
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        loadingTextList = null;
        if (loadingTextList != null && loadingTextList.length > 0) {
            Random random = new Random();
            loadingText.setText(loadingTextList[random.nextInt(loadingTextList.length)]);
        } else {
            loadingText.setText("下拉刷新");
        }
        loadingIv.setVisibility(View.VISIBLE);
        finishIv.setVisibility(View.GONE);
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        startRotate();
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame, boolean isHeader) {
        if (!isHeader) {
            return;
        }
        stopRotate();
    }

    private void startRotate() {
        rotateAnim.start();
    }

    private void stopRotate() {
        rotateAnim.cancel();
        loadingIv.setRotation(0);
        finishIv.setVisibility(View.VISIBLE);
        loadingIv.setVisibility(View.GONE);
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

    }

}
