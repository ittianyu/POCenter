package com.ittianyu.pocenter.features;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ittianyu.pocenter.R;
import com.ittianyu.pocenter.databinding.ActMainBinding;
import com.ittianyu.pocenter.features.find.FindFragment;
import com.ittianyu.pocenter.features.home.HomeFragment;
import com.ittianyu.pocenter.features.mime.MimeFragment;
import com.ittianyu.pocenter.features.version.VersionUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final long TIME_SPACE = 2000;
    private ActMainBinding bind;
    private VpAdapter adapter;
    // collections
    private List<Fragment> fragments;// used for ViewPager adapter
    private long clickTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.act_main);

        initData();
        initView();
        checkVersion();
    }


    private void initData() {
        fragments = new ArrayList<>(3);

        // add fragments
        fragments.add(new HomeFragment());
        fragments.add(new FindFragment());
        fragments.add(new MimeFragment());
    }

    private void initView() {
        // set bnve style
        bind.bnve.enableAnimation(false);

        // set adapter
        adapter = new VpAdapter(getSupportFragmentManager(), fragments);
        bind.vp.setOffscreenPageLimit(2);
        bind.vp.setAdapter(adapter);

        // binding with ViewPager
        bind.bnve.setupWithViewPager(bind.vp);
    }

    private void checkVersion() {
        VersionUtils.check(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                exit();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * if click time space < TIME_SPACE, app will exit
     */
    private void exit() {
        if ((System.currentTimeMillis() - clickTime) > TIME_SPACE) {
            Toast.makeText(getApplicationContext(), R.string.exit_if_repeat,
                    Toast.LENGTH_SHORT).show();
            clickTime = System.currentTimeMillis();
            return;
        }
        finish();
    }

    /**
     * view pager adapter
     */
    private static class VpAdapter extends FragmentPagerAdapter {
        private List<Fragment> data;

        public VpAdapter(FragmentManager fm, List<Fragment> data) {
            super(fm);
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Fragment getItem(int position) {
            return data.get(position);
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
