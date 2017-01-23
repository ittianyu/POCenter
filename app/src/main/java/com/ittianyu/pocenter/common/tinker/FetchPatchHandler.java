package com.ittianyu.pocenter.common.tinker;

import android.os.Handler;
import android.os.Message;

import com.tinkerpatch.sdk.TinkerPatch;

/**
 * Created by zhangshaowen on 17/1/8.
 */

public class FetchPatchHandler extends Handler {
    public static final long HOUR_INTERVAL = 3600 * 1000;

    private long checkInterval;

    /**
     * 通过handler, 达到按照时间间隔轮训的效果
     * @param hour
     */
    public void fetchPatchWithInterval(int hour) {
        //设置TinkerPatch的时间间隔
        TinkerPatch.with().setFetchPatchIntervalByHours(hour);
        checkInterval = hour * HOUR_INTERVAL;
        //立刻尝试去访问,检查是否有更新
        sendEmptyMessage(0);
    }
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);

        //这里使用false即可
        TinkerPatch.with().fetchPatchUpdate(false);
        //每隔一段时间都去访问后台, 增加10分钟的buffer时间
        sendEmptyMessageDelayed(0, checkInterval + 10 * 60 * 1000);
    }
}
