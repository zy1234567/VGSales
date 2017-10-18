package com.ztstech.vgmate.activitys.complete_org_info_v2.subview.org_logo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ztstech.vgmate.R;

/**
 * 编辑机构logo
 */
public class EditOrgLogoActivity extends AppCompatActivity {

    /**传入机构当前url*/
    public static final String ARG_LOGO_URL = "arg_logo_url";

    /**获取的机构结果路径*/
    public static final String RESULT_IMAGE_PATH = "result_img_path";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_org_logo);
    }
}
