package com.ittianyu.pocenter.features.find;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.ittianyu.pocenter.R;
import com.ittianyu.pocenter.common.base.BaseFragment;
import com.ittianyu.pocenter.common.bean.ProjectBean;
import com.ittianyu.pocenter.common.utils.CollectionUtils;
import com.ittianyu.pocenter.databinding.FragFindBinding;
import com.ittianyu.pocenter.features.detail.DetailActivity;
import com.ittianyu.pocenter.features.search.SearchActivity;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yu on 2017/1/13.
 */

public class FindFragment extends BaseFragment<SwipeRefreshLayout, List<ProjectBean>, FindContract.View, FindContract.Presenter>
        implements FindContract.View {
    private FragFindBinding bind;
    private FindAdapter findAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        Logger.d("onCreateView");
        return View.inflate(getContext(), R.layout.frag_find, null);
    }

    @Override
    public FindContract.Presenter createPresenter() {
        return new FindPresenter();
    }

    @Override
    protected void initView(View view) {
        bind = DataBindingUtil.bind(view);

        // init recycler view
        findAdapter = new FindAdapter(new ArrayList<ProjectBean>(0));
        bind.rv.setAdapter(findAdapter);
        bind.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.shape_drive_line));
        bind.rv.addItemDecoration(dividerItemDecoration);

        initEvent();
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
        findAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
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

        // set listener to start search
        bind.tb.findViewById(R.id.ll_root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SearchActivity.class));
            }
        });

        // item click listener
        bind.rv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                // get item url
                ProjectBean project = (ProjectBean) baseQuickAdapter.getItem(i);
                // start detail activity
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_URL, project.url);
                intent.putExtra(DetailActivity.EXTRA_TITLE, project.title);
                intent.putExtra(DetailActivity.EXTRA_DESCRIPTION, project.description);
                startActivity(intent);
            }
        });
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        // disable load more when reload data or first load
        findAdapter.setEnableLoadMore(false);

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
        findAdapter.setNewData(data);

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
        findAdapter.setEnableLoadMore(true);
        bind.vContent.setRefreshing(false);
        Logger.d("load data complete");
    }

    @Override
    public void loadMore() {
        // disable refresh when load more
        bind.vContent.setEnabled(false);

        presenter.loadMore(findAdapter.getData().size());
        Logger.d("start load more");
    }

    @Override
    public void addData(List<ProjectBean> data) {
        if (CollectionUtils.isEmpty(data)) {
            // no more data
            findAdapter.loadMoreEnd();
        } else {
            findAdapter.loadMoreComplete();
        }

        findAdapter.addData(data);
        Logger.d("add data");
        loadMoreComplete();
    }

    @Override
    public void loadMoreError(Throwable e) {
//        showError(e, false);
        findAdapter.loadMoreFail();
        Logger.d("load more error");
        loadMoreComplete();
    }

    @Override
    public void loadMoreComplete() {
        // enable reload after load more completed
        bind.vContent.setEnabled(true);
        Logger.d("load more complete");
    }

}
