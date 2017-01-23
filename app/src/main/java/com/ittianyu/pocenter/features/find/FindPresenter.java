package com.ittianyu.pocenter.features.find;

import com.ittianyu.pocenter.common.api.RemoteApi;
import com.ittianyu.pocenter.common.base.BasePresenter;
import com.ittianyu.pocenter.common.bean.ProjectBean;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by yu on 2017/1/17.
 */

public class FindPresenter extends BasePresenter<FindContract.View> implements FindContract.Presenter {
    private static final int COUNT = 20;

    @Override
    public void loadData(final boolean pullToRefresh) {
        application.getRepertories()
                .getList(0, COUNT, null, RemoteApi.Status.RECRUITING, null, pullToRefresh)
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
                .getList(start, COUNT, null, RemoteApi.Status.RECRUITING, null, false)
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
