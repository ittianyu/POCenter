package com.ittianyu.pocenter.common.base;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.ittianyu.mvp.lcee.MvpLceeView;

/**
 * Created by yu on 2016/11/29.
 */

public interface BaseContract {
    interface View<M> extends MvpLceeView<M> {
        <T> T getApp();
    }

    interface Presenter<V extends View> extends MvpPresenter<V> {
        BaseApplication getApp();
    }

}
