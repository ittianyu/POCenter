package com.ittianyu.pocenter.common.utils;


import java.util.NoSuchElementException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yu on 2016/12/2.
 */
public class RxUtils {
    /**
     * subscribeOn io
     * observeOn mainThread
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> netScheduler() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * If the publish is empty, it wll run onError and throw a NoSuchElementException
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> notEmptyOrError() {
        return new ObservableTransformer<T, T>() {

            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.switchIfEmpty(new Observable<T>() {
                    @Override
                    protected void subscribeActual(Observer<? super T> observer) {
                        observer.onError(new NoSuchElementException());
                    }
                });
            }
        };
    }
}
