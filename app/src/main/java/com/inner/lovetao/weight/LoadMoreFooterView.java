package com.inner.lovetao.weight;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.inner.lovetao.R;

/**
 * desc:
 * Created by xcz
 * on 2019/1/22.
 */
public class LoadMoreFooterView extends LinearLayout {

    private TextView mLoadMoreTv;
    private ProgressBar mLoadMoreProgress;
    private String noMoreText;

    public LoadMoreFooterView(Context context) {
        super(context);
        initView();
    }

    public LoadMoreFooterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LoadMoreFooterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View root = View.inflate(getContext(), R.layout.view_nomore_data_foot, this);
        mLoadMoreTv = (TextView) root.findViewById(R.id.load_more_tv);
        mLoadMoreProgress = (ProgressBar) root.findViewById(R.id.load_more_progress);
    }

    public void showLoadingState() {
        mLoadMoreTv.setVisibility(View.VISIBLE);
        mLoadMoreProgress.setVisibility(View.VISIBLE);
    }

    public void setLoadingText(String text) {
        if (!TextUtils.isEmpty(text)) {
            this.noMoreText = text;
        }
    }

    public void showNoMoreState() {
        mLoadMoreTv.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(noMoreText)) {
            mLoadMoreTv.setText(noMoreText);
        } else {
            mLoadMoreTv.setText("已经全部加载完毕");
        }
        mLoadMoreProgress.setVisibility(View.GONE);
    }

}

