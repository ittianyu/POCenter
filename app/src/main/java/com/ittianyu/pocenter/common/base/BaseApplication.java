package com.ittianyu.pocenter.common.base;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.multidex.MultiDex;

import com.ittianyu.pocenter.BuildConfig;
import com.ittianyu.pocenter.common.api.Repertories;
import com.ittianyu.pocenter.common.tinker.FetchPatchHandler;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tinkerpatch.sdk.TinkerPatch;
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike;

/**
 * Created by yu on 2016/11/24.
 */
public class BaseApplication extends Application {
    private static Context context;
    private static Repertories repertories;

    // tinker patch config
    private ApplicationLike tinkerApplicationLike;

    @Override
    public void onCreate() {
        super.onCreate();

        // tinker patch config
        tinkerPatchConfig();

        context = getApplicationContext();

        initLogger();

        initLeakCanary();

        repertories = new Repertories(context.getCacheDir());
    }

    private void tinkerPatchConfig() {
        // 我们可以从这里获得Tinker加载过程的信息
        if (BuildConfig.TINKER_ENABLE) {
            tinkerApplicationLike = TinkerPatchApplicationLike.getTinkerPatchApplicationLike();

            // 初始化TinkerPatch SDK
            TinkerPatch.init(tinkerApplicationLike)
                    .reflectPatchLibrary()
                    .setPatchRollbackOnScreenOff(true)
                    .setPatchRestartOnSrceenOff(true);

            // 每隔3个小时去访问后台时候有更新,通过handler实现轮训的效果
            new FetchPatchHandler().fetchPatchWithInterval(3);
        }
    }

    /**
     * use LeakCanary to check mey leak
     */
    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    /**
     * init logger
     */
    private void initLogger() {
        if ((0 != (getApplicationInfo().flags &= ApplicationInfo.FLAG_DEBUGGABLE)))
            Logger.init(); // for debug, print all log
        else
            Logger.init().logLevel(LogLevel.NONE); // for release, remove all log
//            Logger.init(); // for release, remove all log
    }

    public static Context getContext() {
        return context;
    }

    public static Repertories getRepertories() {
        return repertories;
    }


    /**
     * tinker
     * @param base
     */
    @Override
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
    }
}
