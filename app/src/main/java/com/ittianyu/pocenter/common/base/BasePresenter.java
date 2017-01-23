package com.ittianyu.pocenter.common.base;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;

/**
 * Created by yu on 2016/11/25.
 */

public class BasePresenter<V extends BaseContract.View>
        extends MvpNullObjectBasePresenter<V>
        implements BaseContract.Presenter<V>{
    protected BaseApplication application;

    @Override
    public void attachView(V view) {
        super.attachView(view);
        application = (BaseApplication) getView().getApp();
    }

    @Override
    public BaseApplication getApp() {
        return application;
    }
}
