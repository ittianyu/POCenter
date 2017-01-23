package com.ittianyu.pocenter.common.api;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.ittianyu.pocenter.R;
import com.ittianyu.pocenter.common.base.BaseApplication;
import com.ittianyu.pocenter.common.bean.ProjectBean;
import com.ittianyu.pocenter.common.utils.ConfigUtils;
import com.ittianyu.pocenter.common.utils.RxUtils;
import com.ittianyu.pocenter.common.utils.UnsafeOkHttpUtils;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yu on 2017/1/17.
 */
public class Repertories {
    public static final String HOST = "po.ittianyu.com";
    public static final String URL_BASE = "https://" + HOST + "/";

    private RemoteApi remoteApi;
    private CacheApi cacheApi;
    private List<String> types = new ArrayList<>();

    public Repertories(File cacheDir) {
        // create apis
        cacheApi = new RxCache.Builder()
                .useExpiredDataIfLoaderNotAvailable(true)
                .persistence(cacheDir, new GsonSpeaker())
                .using(CacheApi.class);

        remoteApi = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(UnsafeOkHttpUtils.getClient())
                .build()
                .create(RemoteApi.class);

        // add types
        Context context = BaseApplication.getContext();
        types.add(context.getString(R.string.type_other));
        types.add(context.getString(R.string.type_web));
        types.add(context.getString(R.string.type_we_chat));
        types.add(context.getString(R.string.type_html5));
        types.add(context.getString(R.string.type_app));
        types.add(context.getString(R.string.type_intelligent_hardware));
        types.add(context.getString(R.string.type_desktop_app));
        types.add(context.getString(R.string.type_big_data));
        types.add(context.getString(R.string.type_system));
        types.add(context.getString(R.string.type_sdk_api));
        types.add(context.getString(R.string.type_art));
    }

    public Observable<List<ProjectBean>> getList(int start, int count, int[] types, int status, String[] keywords, boolean update) {
        String key = generateKey(start, count, types, status, keywords);
        return cacheApi.getList(remoteApi.getList(start, count, types, status, keywords),
                new DynamicKey(key), new EvictDynamicKey(update))
                .compose(RxUtils.<List<ProjectBean>>netScheduler());// net on io thread, subscribe on main thread
//        return remoteApi.getList(start, count, types, status, keywords)
//                .compose(RxUtils.<List<ProjectBean>>netScheduler());// net on io thread, subscribe on main thread
    }

    /**
     * according params to generate key
     * @param start
     * @param count
     * @param types
     * @param status
     * @param keywords
     * @return
     */
    @NonNull
    private String generateKey(int start, int count, int[] types, int status, String[] keywords) {
        StringBuilder keyBuilder = new StringBuilder("start=" + start + "&count=" + count);
        if (null != types && types.length > 0) {
            Arrays.sort(types);
            for (int type : types) {
                keyBuilder.append("&type=" + type);
            }
        }
        keyBuilder.append("&status=" + status);
        if (null != keywords && keywords.length > 0) {
            Arrays.sort(keywords);
            for (String keyword : keywords) {
                keyBuilder.append("&keyword=" + keyword);
            }
        }
        return keyBuilder.toString();
    }

    public List<String> getAllTypes() {
        return types;
    }

    public boolean isSettingTypes() {
        // load config
        String type = ConfigUtils.getString(BaseApplication.getContext(), ConfigUtils.KEY_TYPE, "");
        if (TextUtils.isEmpty(type))
            return false;
        return true;
    }

    public int[] getTypes() {
        String type = ConfigUtils.getString(BaseApplication.getContext(), ConfigUtils.KEY_TYPE, "");
        if (TextUtils.isEmpty(type))
            return null;
        String[] typesString = type.split(",");
        if (0 == typesString.length)
            return null;

        int[] types = new int[typesString.length];
        for (int i = 0; i < typesString.length; i++) {
            types[i] = Integer.parseInt(typesString[i]);
        }
        return types;
    }

    public void setTypes(Set<Integer> set) {
        // create types string
        StringBuilder typeBuilder = new StringBuilder();
        for (int type : set) {
            typeBuilder.append(type);
            typeBuilder.append(',');
        }
        typeBuilder.deleteCharAt(typeBuilder.length() - 1);// delete the last ','
        // save to file
        ConfigUtils.putString(BaseApplication.getContext(), ConfigUtils.KEY_TYPE, typeBuilder.toString());
    }
}
