package com.ittianyu.pocenter.common.api;

import com.ittianyu.pocenter.common.bean.ProjectBean;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.LifeCache;

/**
 * Created by yu on 2017/1/17.
 */

public interface CacheApi {

    @LifeCache(duration = 15, timeUnit = TimeUnit.MINUTES)
    Observable<List<ProjectBean>> getList(Observable<List<ProjectBean>> repo, DynamicKey key, EvictDynamicKey isUpdate);
}
