package com.ittianyu.pocenter.features.search;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ittianyu.mvp.lcee.Lcee;
import com.ittianyu.mvp.lcee.MvpLceeActivity;
import com.ittianyu.pocenter.R;
import com.ittianyu.pocenter.common.bean.ProjectBean;
import com.ittianyu.pocenter.common.utils.CollectionUtils;
import com.ittianyu.pocenter.databinding.ActSearchBinding;
import com.ittianyu.pocenter.features.detail.DetailActivity;
import com.orhanobut.logger.Logger;
import com.umeng.analytics.MobclickAgent;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yu on 2017/1/13.
 */
@Lcee(loadingViewId = R.id.v_loading, contentViewId = R.id.v_content, errorViewId = R.id.v_error, emptyViewId = R.id.v_empty)
public class SearchActivity extends MvpLceeActivity<SwipeRefreshLayout, List<ProjectBean>, SearchContract.View, SearchContract.Presenter>
        implements SearchContract.View {
    private ActSearchBinding bind;
    private SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bind = DataBindingUtil.setContentView(this, R.layout.act_search);
        initView();
    }

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

    @Override
    public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
        RotateLoading rotateLoading = (RotateLoading) this.loadingView.findViewById(R.id.rl);
        if (!rotateLoading.isStart())
            rotateLoading.start();
    }

    @Override
    public SearchContract.Presenter createPresenter() {
        return new SearchPresenter();
    }

    protected void initView() {
        // init recycler view
        searchAdapter = new SearchAdapter(new ArrayList<ProjectBean>(0));
        bind.rv.setAdapter(searchAdapter);
        bind.rv.setLayoutManager(new LinearLayoutManager(this));
//        bind.rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        bind.fsv.setSearchFocused(true);

        initEvent();
        showContent();
    }

    /**
     * set listeners
     */
    private void initEvent() {
        // refresh listener
        bind.vContent.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData(true);
            }
        });
        // load more listener
        searchAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                bind.rv.post(new Runnable() {
                    @Override
                    public void run() {
                        loadMore();
                    }
                });
            }
        });

        // item click listener
        bind.rv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                // get item url
                ProjectBean project = (ProjectBean) baseQuickAdapter.getItem(i);
                // start detail activity
                Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_URL, project.url);
                intent.putExtra(DetailActivity.EXTRA_TITLE, project.title);
                intent.putExtra(DetailActivity.EXTRA_DESCRIPTION, project.description);
                startActivity(intent);
            }
        });

        // search listener
        bind.fsv.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

            }

            @Override
            public void onSearchAction(String currentQuery) {
                if (!TextUtils.isEmpty(currentQuery)) {
                    presenter.setSearchString(currentQuery);
                    loadData(false);
                }
            }
        });
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        // disable load more when reload data or first load
        searchAdapter.setEnableLoadMore(false);

        // show loading
        showLoading(pullToRefresh);

        presenter.loadData(pullToRefresh);
        Logger.d("start load data");
    }

    @Override
    public void setData(List<ProjectBean> data) {
        if (CollectionUtils.isEmpty(data)) {
            showEmpty();
        } else {
            showContent();
        }
        searchAdapter.setNewData(data);
        bind.rv.scrollToPosition(0);

        Logger.d("set data");
        loadDataComplete();
    }

    @Override
    public void loadDataError(Throwable e, boolean pullToRefresh) {
        showError(e, pullToRefresh);
        Logger.d("load data error");
        loadDataComplete();
    }

    @Override
    public void loadDataComplete() {
        // enable load more after reload completed
        searchAdapter.setEnableLoadMore(true);
        bind.vContent.setRefreshing(false);
        Logger.d("load data complete");
    }

    @Override
    public void loadMore() {
        // disable refresh when load more
        bind.vContent.setEnabled(false);

        presenter.loadMore(searchAdapter.getData().size());
        Logger.d("start load more");
    }

    @Override
    public void addData(List<ProjectBean> data) {
        if (CollectionUtils.isEmpty(data)) {
            // no more data
            searchAdapter.loadMoreEnd();
        } else {
            searchAdapter.loadMoreComplete();
        }

        searchAdapter.addData(data);
        Logger.d("add data");
        loadMoreComplete();
    }

    @Override
    public void loadMoreError(Throwable e) {
//        showError(e, false);
        searchAdapter.loadMoreFail();
        Logger.d("load more error");
        loadMoreComplete();
    }

    @Override
    public void loadMoreComplete() {
        // enable reload after load more completed
        bind.vContent.setEnabled(true);
        Logger.d("load more complete");
    }

    @Override
    public <T> T getApp() {
        return (T) getApplication();
    }

    // 友盟统计
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    // 友盟统计 结束
}
