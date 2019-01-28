package com.inner.lovetao.loginregister;

import android.os.CountDownTimer;

/**
 * desc:计时器工具
 * Created by xcz
 * on 2018/12/6.
 */
public class TimeCount extends CountDownTimer {

    private CountDownTimerListener countDownTimerListener;
    private String phone;
    private long lastTime;

    public TimeCount(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        if (countDownTimerListener != null) {
            countDownTimerListener.init();
        }
    }


    @Override
    public void onFinish() {// 计时完毕时触发
        if (countDownTimerListener != null) {
            countDownTimerListener.timeover();
        }
        phone = null;
        cancel();
    }

    @Override
    public void onTick(long millisUntilFinished) {// 计时过程显示
        lastTime = millisUntilFinished / 1000;
        if (countDownTimerListener != null) {
            countDownTimerListener.update(millisUntilFinished);
        }
    }

    public CountDownTimerListener getCountDownTimerListener() {
        return countDownTimerListener;
    }

    public void setCountDownTimerListener(CountDownTimerListener countDownTimerListener) {
        this.countDownTimerListener = countDownTimerListener;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public interface CountDownTimerListener {
        /**
         * 初始化
         */
        void init();

        /**
         * 计时结束
         */
        void timeover();

        /**
         * 更新控件
         */
        void update(long millisUntilFinished);
    }
}
