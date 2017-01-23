package com.ittianyu.pocenter.features.type;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

/**
 * Created by yu on 2017/1/17.
 */

public interface TypeContract {
    interface View extends MvpView {
        void setData(List<String> data, int[] types);
    }

    interface Presenter extends MvpPresenter<View> {
        void loadData();
        boolean isFirstSetting();
    }
}
