package com.ztstech.vgmate.activitys.enroll_tag;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;

/**
 * 招生标签
 */
public class EnrollTagActivity extends MVPActivity<EnrollTagContract.Presenter> implements
        EnrollTagContract.View {


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_enroll_tag;
    }

    @Override
    protected EnrollTagContract.Presenter initPresenter() {
        return null;
    }
}
