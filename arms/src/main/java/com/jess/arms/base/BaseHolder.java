package com.jess.arms.base;

import android.view.View;

import com.jess.arms.utils.ThirdViewUtil;
import com.zhy.autolayout.utils.AutoUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * ================================================
 * 基类 {@link RecyclerView.ViewHolder}
 * <p>
 * ================================================
 */
public abstract class BaseHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected OnViewClickListener mOnViewClickListener = null;
    protected final String TAG = this.getClass().getSimpleName();

    public BaseHolder(View itemView) {
        super(itemView);
        //点击事件
        itemView.setOnClickListener(this);
        //屏幕适配
        if (ThirdViewUtil.isUseAutolayout()) AutoUtils.autoSize(itemView);
        //绑定 ButterKnife
        ThirdViewUtil.bindTarget(this, itemView);
    }

    /**
     * 设置数据
     *
     * @param data     数据
     * @param position 在 RecyclerView 中的位置
     */
    public abstract void setData(@NonNull T data, int position);

    /**
     * 在 Activity 的 onDestroy 中使用  方法 (super.onDestroy() 之前)
     * {@link BaseHolder#onRelease()} 才会被调用, 可以在此方法中释放一些资源
     */
    protected void onRelease() {

    }

    @Override
    public void onClick(View view) {
        if (mOnViewClickListener != null) {
            mOnViewClickListener.onViewClick(view, this.getPosition());
        }
    }

    /**
     * item 点击事件
     */
    public interface OnViewClickListener {

        /**
         * item 被点击
         *
         * @param view     被点击的 {@link View}
         * @param position 在 RecyclerView 中的位置
         */
        void onViewClick(View view, int position);
    }

    public void setOnItemClickListener(OnViewClickListener listener) {
        this.mOnViewClickListener = listener;
    }
}