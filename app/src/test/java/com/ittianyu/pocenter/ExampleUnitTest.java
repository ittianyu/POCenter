package com.ittianyu.pocenter;

import com.ittianyu.pocenter.common.utils.UnsafeOkHttpUtils;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.junit.Test;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
        public static final String HOST = "po.ittianyu.com";
    public static final String URL_BASE = "https://" + HOST + "/";
//    public static final String HOST = "192.168.1.106";
//    public static final String URL_BASE = "http://" + HOST + "/pocenter/";

    interface RemoteApi {
        @GET("list")
        Observable<ResponseBody> getList(@Query("start") int start, @Query("count") int count,
                           @Query("type") int[] types, @Query("status") int status,
                           @Query(value = "keyword") String[] keywords);
    }


    @Test
    public void testSearch() throws IOException {
        RemoteApi remoteApi = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
                .client(UnsafeOkHttpUtils.getClient())
                .build()
                .create(RemoteApi.class);
        remoteApi.getList(0, 20, null, 0, new String[]{"微信"})
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        String string = responseBody.string();
                        System.out.println(string);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });

    }

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}