package com.ittianyu.pocenter.features.version;

import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.allenliu.versionchecklib.AVersionService;
import com.google.gson.Gson;
import com.ittianyu.pocenter.BuildConfig;
import com.ittianyu.pocenter.R;
import com.ittianyu.pocenter.common.utils.UnsafeOkHttpUtils;
import com.zhy.http.okhttp.OkHttpUtils;

public class CheckVersionService extends AVersionService {
    public static final String SHOW_LAST = "showLast";

    private boolean showLast = false;

    public CheckVersionService() {
        OkHttpUtils.initClient(UnsafeOkHttpUtils.getClient());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showLast = intent.getBooleanExtra(SHOW_LAST, showLast);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onResponses(AVersionService service, String response) {
        Gson gson = new Gson();
        VersionBean versionBean = gson.fromJson(response, VersionBean.class);
        if (versionBean.versionCode > BuildConfig.VERSION_CODE) {
            //传入下载地址，以及版本更新消息
            service.showVersionDialog(versionBean.apkUrl, versionBean.changeLog);
        } else {
            if (showLast)
                Toast.makeText(service.getApplicationContext(), R.string.tips_already_last_version, Toast.LENGTH_SHORT).show();
            //由于是回调方法，当不进行版本升级时，需要手动关闭service。需要进行版本升级时，由库管理生命周期
            stopSelf();
        }
    }
}
