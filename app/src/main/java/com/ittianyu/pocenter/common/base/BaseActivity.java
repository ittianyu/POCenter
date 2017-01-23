package com.ittianyu.pocenter.common.base;

import android.view.View;
import android.widget.TextView;

import com.ittianyu.mvp.lcee.Lcee;
import com.ittianyu.mvp.lcee.MvpLceeActivity;
import com.ittianyu.pocenter.R;
import com.orhanobut.logger.Logger;

/**
 * Created by yu on 2016/11/25.
 */
@Lcee(loadingViewId = R.id.v_loading, contentViewId = R.id.v_content, errorViewId = R.id.v_error, emptyViewId = R.id.v_empty)
public abstract class BaseActivity<CV extends View, M, V extends BaseContract.View<M>, P extends BaseContract.Presenter<V>>
        extends MvpLceeActivity<CV, M, V, P> implements BaseContract.View<M> {
//    protected View view;// cache view
//    protected boolean firstLoad = true;

    @Override
    protected void onStart() {
        super.onStart();
        initView();
        loadData(false);
    }

//    /**
//     * create view
//     * It will be only create once in a life
//     * No need set content view when onCreate
//     */
//    protected abstract View createView();

    /**
     * init view
     */
    protected abstract void initView();

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        Logger.e(e, "");
        return pullToRefresh ? getString(R.string.failed_to_refresh) : getString(R.string.failed_to_load_click_to_reload);
    }

    @Override
    protected void onSetErrorViewText(View errorView, String errorMsg) {
        TextView tv = (TextView) errorView.findViewById(R.id.tv);
        tv.setText(errorMsg);
    }

}
