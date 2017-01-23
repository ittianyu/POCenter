package com.ittianyu.pocenter.features.find;

import com.ittianyu.pocenter.common.base.BaseContract;
import com.ittianyu.pocenter.common.bean.ProjectBean;

import java.util.List;

/**
 * Created by yu on 2017/1/17.
 */

public interface FindContract {
    interface View extends BaseContract.View<List<ProjectBean>> {
        void loadDataError(Throwable e, boolean pullToRefresh);
        void loadDataComplete();
        void loadMore();
        void addData(List<ProjectBean> data);
        void loadMoreError(Throwable e);
        void loadMoreComplete();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void loadData(boolean pullToRefresh);
        void loadMore(int start);
    }
}
