package com.ittianyu.pocenter.features;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ittianyu.pocenter.R;
import com.ittianyu.pocenter.common.base.BaseApplication;
import com.ittianyu.pocenter.features.type.TypeActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class SplashActivity extends AppCompatActivity {
    private static final long SHOW_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splash);

        // wait for 1s to show splash activity
        Observable.just(new Object())
                .delay(SHOW_TIME, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        start();
                    }
                });
    }

    /**
     * start load config
     */
    private void start() {
        // check type
        if (!BaseApplication.getRepertories().isSettingTypes()) {
            // type is empty, need start type activity
            startActivity(new Intent(this, TypeActivity.class));
        } else {
            // enter main activity
            startActivity(new Intent(this, MainActivity.class));
        }
        finish();
    }

    // 友盟统计
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    // 友盟统计 结束
}
