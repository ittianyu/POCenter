package com.ittianyu.pocenter.features.version;

import android.content.Context;
import android.content.Intent;

import com.allenliu.versionchecklib.VersionParams;
import com.ittianyu.pocenter.common.api.Repertories;

/**
 * Created by yu on 2017/1/19.
 */
public class VersionUtils {
    /**
     * check version
     * @param context
     */
    public static void check(Context context) {
        check(context, false);
    }

    /**
     * check version
     * @param context
     * @param showLast it will show a tip tell user the version is last if true.
     */
    public static void check(Context context, boolean showLast) {
        VersionParams versionField = new VersionParams()
                //是否强制升级,默认false
                .setIsForceUpdate(false)
                //当版本接口请求失败时，service会根据设定的间隔时间继续请求版本接口，
                // 直到手动关闭service或者接口请求成功，不填默认10s
//                .setPauseRequestTime(requestTime)
                //接口地址，必填
                .setRequestUrl(Repertories.URL_BASE + "version.json")
                //自定service包名,必须填写用于开启service
                .setVersionServiceName(CheckVersionService.class.getName());
        Intent intent = new Intent(context, CheckVersionService.class);
        intent.putExtra("versionField", versionField);
        intent.putExtra(CheckVersionService.SHOW_LAST, showLast);
        context.startService(intent);
    }
}
