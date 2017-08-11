package com.ztstech.vgmate.activitys.complete_info;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.main.MainActivity;


public class FillInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_info);
    }

    /**
     * 点击提交
     * @param view
     */
    public void onSubmitClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
