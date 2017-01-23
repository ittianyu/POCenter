package com.ittianyu.pocenter.features.search;

import com.ittianyu.pocenter.common.api.RemoteApi;
import com.ittianyu.pocenter.common.base.BasePresenter;
import com.ittianyu.pocenter.common.bean.ProjectBean;
import com.orhanobut.logger.Logger;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by yu on 2017/1/17.
 */
public class SearchPresenter extends BasePresenter<SearchContract.View> implements SearchContract.Presenter {
    private static final int COUNT = 20;
    private String[] keywords;

    @Override
    public void loadData(final boolean pullToRefresh) {
        application.getRepertories()
                .getList(0, COUNT, null, RemoteApi.Status.RECRUITING, keywords, pullToRefresh)
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
                .getList(start, COUNT, null, RemoteApi.Status.RECRUITING, keywords, false)
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

    @Override
    public void setSearchString(String search) {
        keywords = search.split(" ");
        Logger.d(keywords);
    }
}
