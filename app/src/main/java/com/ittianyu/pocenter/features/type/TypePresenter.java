package com.ittianyu.pocenter.features.type;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.ittianyu.pocenter.common.api.Repertories;
import com.ittianyu.pocenter.common.base.BaseApplication;

import java.util.List;

/**
 * Created by yu on 2017/1/17.
 */

public class TypePresenter extends MvpNullObjectBasePresenter<TypeContract.View> implements TypeContract.Presenter {
    private int[] types;

    @Override
    public void loadData() {
        // load all types
        Repertories repertories = BaseApplication.getRepertories();
        List<String> allTypes = repertories.getAllTypes();
        // load selected types
        types = repertories.getTypes();
        // set data
        getView().setData(allTypes, types);
    }

    @Override
    public boolean isFirstSetting() {
        return null == types;
    }
}
