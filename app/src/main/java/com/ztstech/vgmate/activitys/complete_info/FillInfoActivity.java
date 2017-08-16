package com.ztstech.vgmate.activitys.complete_info;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.location_select.LocationSelectActivity;
import com.ztstech.vgmate.activitys.main.MainActivity;
import com.ztstech.vgmate.base.BaseActivity;

import butterknife.BindView;

/**
 * 补充信息
 */
public class FillInfoActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.et_location)
    TextInputEditText etLocation;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_fill_info;
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();

        etLocation.setOnClickListener(this);
    }

    /**
     * 点击提交
     * @param view
     */
    public void onSubmitClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
        if (view == etLocation) {
            startActivity(new Intent(this, LocationSelectActivity.class));
        }
    }
}
