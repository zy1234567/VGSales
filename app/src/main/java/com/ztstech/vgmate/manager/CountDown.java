package com.ztstech.vgmate.manager;

import android.os.Handler;

/**
 * Created by zhiyuan on 2017/10/28.
 * 倒计时工具类
 */

public class CountDown implements Runnable{


    private Handler handler;

    /**
     * 总共倒计时时长
     */
    private final int TOTAL_SECONDS;

    /**
     * 当前倒计时事件
     */
    private int seconds;

    private CountdownListener listener;

    /**
     * 是否正在计时中
     */
    private boolean counting;

    private boolean cancel;

    public CountDown(int seconds) {
        TOTAL_SECONDS = seconds;
        this.seconds = TOTAL_SECONDS;
        handler = new Handler();
    }

    public int getSeconds() {
        return seconds;
    }

    /**
     * 是否正在计时
     * @return
     */
    public boolean isCounting() {
        return counting;
    }

    public void start() {
        if (counting) {
            throw new IllegalStateException("正在运行的计时器不能再次运行");
        }
        counting = true;
        handler.postDelayed(this, 1000);
    }

    public void setListener(CountdownListener listener) {
        this.listener = listener;
    }

    public void cancel() {
        cancel = true;
        counting = false;
    }


    @Override
    public void run() {
        if (cancel) {
            return;
        }
        seconds--;
        if (seconds >= 0) {
            handler.postDelayed(this, 1000);
            if (this.listener != null) {
                this.listener.updateSeconds(seconds);
            }
        }else {
            //回调结束
            this.listener.updateSeconds(0);
            seconds = TOTAL_SECONDS;
        }
        if (seconds == 0) {
            counting = false;
        }

    }

    public interface CountdownListener {

        void updateSeconds(int seconds);
    }
}
