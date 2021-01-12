package com.yun.library;

import android.os.CountDownTimer;

/**
 * 倒计时工具类
 *
 * @author yun
 * @date 2018/12/4 17:27
 * @Description:
 */
public class CountDownTool {

    /**
     * 倒计时结束的回调接口
     */
    public interface FinishDelegate {
        void onFinish();
    }

    /**
     * 定期回调的接口
     */
    public interface TickDelegate {
        void onTick(long millisUntilFinished);
    }

    private final static long ONE_SECOND = 1000;
    /**
     * 总倒计时时间
     */
    private long mMillisInFuture = 10000;
    /**
     * 定期回调的时间 必须大于0 否则会出现ANR
     */
    private long mCountDownInterval = 1000;
    /**
     * 倒计时结束的回调
     */
    private FinishDelegate mFinishDelegate;
    /**
     * 定期回调
     */
    private TickDelegate mTickDelegate;
    private CountDownCustomUtil mCountDownTimer;

    /**
     * 获取 CountDownTimerUtils
     *
     * @return CountDownTimerUtils
     */
    public static CountDownTool getCountDownTimer() {
        return new CountDownTool();
    }

    /**
     * 创建
     */
    public void create() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
        if (mCountDownInterval <= 0) {
            mCountDownInterval = mMillisInFuture + ONE_SECOND;
        }
        mCountDownTimer = new CountDownCustomUtil(mMillisInFuture, mCountDownInterval);
        mCountDownTimer.setTickDelegate(mTickDelegate);
        mCountDownTimer.setFinishDelegate(mFinishDelegate);
    }

    /**
     * 设置定期回调的时间 调用{@link #setTickDelegate(TickDelegate)}
     *
     * @param pCountDownInterval 定期回调的时间 必须大于0
     * @return CountDownTimerUtils
     */
    public CountDownTool setCountDownInterval(long pCountDownInterval) {
        this.mCountDownInterval = pCountDownInterval;
        return this;
    }

    /**
     * 设置倒计时结束的回调
     *
     * @param pFinishDelegate 倒计时结束的回调接口
     * @return CountDownTimerUtils
     */
    public CountDownTool setFinishDelegate(FinishDelegate pFinishDelegate) {
        this.mFinishDelegate = pFinishDelegate;
        return this;
    }

    /**
     * 设置总倒计时时间
     *
     * @param pMillisInFuture 总倒计时时间
     * @return CountDownTimerUtils
     */
    public CountDownTool setMillisInFuture(long pMillisInFuture) {
        this.mMillisInFuture = pMillisInFuture;
        return this;
    }

    /**
     * 设置定期回调
     *
     * @param pTickDelegate 定期回调接口
     * @return CountDownTimerUtils
     */
    public CountDownTool setTickDelegate(TickDelegate pTickDelegate) {
        this.mTickDelegate = pTickDelegate;
        return this;
    }

    /**
     * 开始倒计时
     */
    public void start() {
        create();
        mCountDownTimer.start();
    }

    /**
     * 取消倒计时
     */
    public void cancel() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

    public static class CountDownCustomUtil extends CountDownTimer {
        private FinishDelegate mFinishDelegate;
        private TickDelegate mTickDelegate;

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public CountDownCustomUtil(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (mTickDelegate != null) {
                mTickDelegate.onTick(millisUntilFinished);
            }
        }

        @Override
        public void onFinish() {
            if (mFinishDelegate != null) {
                mFinishDelegate.onFinish();
            }
        }

        void setFinishDelegate(FinishDelegate pFinishDelegate) {
            this.mFinishDelegate = pFinishDelegate;
        }

        void setTickDelegate(TickDelegate pTickDelegate) {
            this.mTickDelegate = pTickDelegate;
        }
    }

}