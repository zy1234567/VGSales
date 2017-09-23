package com.ztstech.vgmate.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zhiyuan on 2017/7/27.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder mUnbinder;

    private View contentView;

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (contentView == null) {
            contentView = inflater.inflate(getLayoutRes(), container, false);
        }
        mUnbinder = ButterKnife.bind(this, contentView);
        onViewBindFinish(savedInstanceState);
        return contentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {}

    protected abstract int getLayoutRes();


}

