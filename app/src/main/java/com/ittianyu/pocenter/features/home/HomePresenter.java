package com.ittianyu.pocenter.features.home;

import com.ittianyu.pocenter.common.api.RemoteApi;
import com.ittianyu.pocenter.common.base.BaseApplication;
import com.ittianyu.pocenter.common.base.BasePresenter;
import com.ittianyu.pocenter.common.bean.ProjectBean;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by yu on 2017/1/17.
 */

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {
    private static final int COUNT = 20;
    private int[] types;

    public HomePresenter() {
        loadTypes();
    }

    /**
     * load user selected types from config
     */
    private void loadTypes() {
        // load types
        types = BaseApplication.getRepertories().getTypes();
    }

    @Override
    public void loadData(final boolean pullToRefresh) {
        // if refresh data, update types first
        if (pullToRefresh)
            loadTypes();

        application.getRepertories()
                .getList(0, COUNT, types, RemoteApi.Status.RECRUITING, null, pullToRefresh)
                .subscribe(new Consumer<List<ProjectBean>>() {
                    @Override
                    public void accept(List<ProjectBean> projectBeen) throws Exception {
                        getView().setData(projectBeen);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().loadDataError(throwable, pullToRefresh);
                    }
                });
    }

    @Override
    public void loadMore(int start) {
        application.getRepertories()
                .getList(start, COUNT, types, RemoteApi.Status.RECRUITING, null, false)
                .subscribe(new Consumer<List<ProjectBean>>() {
                    @Override
                    public void accept(List<ProjectBean> projectBeen) throws Exception {
                        getView().addData(projectBeen);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().loadMoreError(throwable);
                    }
                });
    }
}
