package com.ittianyu.pocenter.features.type;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.ittianyu.pocenter.R;
import com.ittianyu.pocenter.common.base.BaseApplication;
import com.ittianyu.pocenter.databinding.ActTypeBinding;
import com.ittianyu.pocenter.features.MainActivity;
import com.orhanobut.logger.Logger;
import com.umeng.analytics.MobclickAgent;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TypeActivity extends MvpActivity<TypeContract.View, TypeContract.Presenter>
        implements TypeContract.View {
    private ActTypeBinding bind;
    private MyTagAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.act_type);
        bind = DataBindingUtil.setContentView(this, R.layout.act_type);

        initView();
        presenter.loadData();
        initEvent();
    }

    @NonNull
    @Override
    public TypeContract.Presenter createPresenter() {
        return new TypePresenter();
    }

    private void initView() {
        // init action bar
        setSupportActionBar(bind.tb);
    }

    private void initEvent() {
        bind.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Set<Integer> list = bind.tfl.getSelectedList();
                Logger.d(list);
                if (list.isEmpty()) {
                    Toast.makeText(TypeActivity.this, R.string.toast_select_one_or_more_type, Toast.LENGTH_SHORT).show();
                    return;
                }
                // save selected types
                BaseApplication.getRepertories().setTypes(list);

                // start activity
                if (presenter.isFirstSetting())
                    startActivity(new Intent(TypeActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void setData(List<String> data, int[] types) {
        adapter = new MyTagAdapter(data);
        bind.tfl.setAdapter(adapter);
        // set selected list
        if (null != types)
            adapter.setSelectedList(types);
    }

    /**
     * create menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_type, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.i_select_all:
                selectAll();
                break;
            case R.id.i_cancel_all:
                cancelAll();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * cancel all selected item
     */
    private void cancelAll() {
        adapter.setSelectedList(new HashSet<Integer>(0));
    }

    /**
     * select all item
     */
    private void selectAll() {
        int count = adapter.getCount();
        int[] list = new int[count];
        for (int i = 0; i < count; i++) {
            list[i] = i;
        }
        adapter.setSelectedList(list);
    }

    private static class MyTagAdapter extends TagAdapter<String> {

        public MyTagAdapter(List<String> data) {
            super(data);
        }

        @Override
        public View getView(FlowLayout parent, int position, String s) {
            TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.view_type, parent, false);
            tv.setText(s);
            return tv;
        }
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
