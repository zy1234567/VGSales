package com.ztstech.mate.activitys.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ztstech.mate.R;
import com.ztstech.mate.activitys.complete_info.FillInfoActivity;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }


    public void onNextClick(View view) {
        startActivity(new Intent(this, FillInfoActivity.class));
        finish();
    }
}

