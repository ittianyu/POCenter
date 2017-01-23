package com.ittianyu.pocenter.features.mime;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ittianyu.pocenter.BuildConfig;
import com.ittianyu.pocenter.R;
import com.ittianyu.pocenter.databinding.FragMimeBinding;
import com.ittianyu.pocenter.features.type.TypeActivity;
import com.ittianyu.pocenter.features.version.VersionUtils;

/**
 * Created by yu on 2017/1/13.
 */

public class MimeFragment extends Fragment {

    private FragMimeBinding bind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return View.inflate(getContext(), R.layout.frag_mime, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initEvent();
    }

    private void initView(View view) {
        bind = DataBindingUtil.bind(view);
    }

    private void initEvent() {
        // tag manager
        bind.llTagManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), TypeActivity.class));
            }
        });

        // about
        bind.llAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getContext();
                new AlertDialog.Builder(context)
                        .setTitle(context.getString(R.string.app_name) + " (" + BuildConfig.VERSION_NAME + ")")
                        .setMessage(R.string.tips_about_us)
                        .setPositiveButton(R.string.ok, null)
                        .show();
            }
        });

        // version check
        bind.llVersionCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VersionUtils.check(getContext(), true);
            }
        });
    }
}
