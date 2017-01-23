package com.ittianyu.pocenter.common.api;

import com.ittianyu.pocenter.common.bean.ProjectBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yu on 2017/1/17.
 */

public interface RemoteApi {
    interface Type {
        int OTHER = 0;
        int WEB = 1;
        int WE_CHAT = 2;
        int HTML5 = 3;
        int APP = 4;
        int INTELLIGENT_HARDWARE = 5;
        int DESKTOP_APP = 6;
        int BIG_DATA = 7;
        int SYSTEM = 8;
        int SDK_API = 9;
        int ART = 10;
    }
    interface Status {
        int RECRUITING = 0;
        int RECRUITED = 1;
    }

    /**
     * select list according to types status and keywords
     *
     * @param start    start index
     * @param count    select count
     * @param types    support multiple types
     * @param status
     * @param keywords title or description keywords
     * @return
     */
    @GET("list")
    Observable<List<ProjectBean>> getList(@Query("start") int start, @Query("count") int count,
                                          @Query("type") int[] types, @Query("status") int status,
                                          @Query("keyword") String[] keywords);
}
