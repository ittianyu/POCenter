package com.ittianyu.pocenter.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.ittianyu.mvp.lcee.Lcee;
import com.ittianyu.mvp.lcee.MvpLceeFragment;
import com.ittianyu.pocenter.R;
import com.orhanobut.logger.Logger;
import com.victor.loading.rotate.RotateLoading;

/**
 * Created by yu on 2016/11/25.
 */
@Lcee(loadingViewId = R.id.v_loading, contentViewId = R.id.v_content, errorViewId = R.id.v_error, emptyViewId = R.id.v_empty)
public abstract class BaseFragment<CV extends View, M, V extends BaseContract.View<M>, P extends BaseContract.Presenter<V>>
        extends MvpLceeFragment<CV, M, V, P> implements BaseContract.View<M> {
    protected boolean firstLoad = true;
    protected boolean prepared = false;
    protected boolean visible = false;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        visible = isVisibleToUser;

        lazyLoad();
    }

    /**
     * only load data at first visible
     */
    private void lazyLoad() {
        if (visible && firstLoad && prepared) {
            firstLoad = false;
            loadData(false);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        showLoading(false);
        prepared = true;
        lazyLoad();
    }

    /**
     * init view
     */
    protected abstract void initView(View view);

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        Logger.e(e, e.getMessage());
        return pullToRefresh ? getString(R.string.failed_to_refresh) : getString(R.string.failed_to_load_click_to_reload);
    }

    @Override
    protected void onSetErrorViewText(View errorView, String errorMsg) {
        TextView tv = (TextView) errorView.findViewById(R.id.tv);
        tv.setText(errorMsg);
    }

    @Override
    public <T> T getApp() {
        return (T) getActivity().getApplication();
    }

    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
        RotateLoading rotateLoading = (RotateLoading) this.loadingView.findViewById(R.id.rl);
        if (!rotateLoading.isStart())
            rotateLoading.start();
    }
}
