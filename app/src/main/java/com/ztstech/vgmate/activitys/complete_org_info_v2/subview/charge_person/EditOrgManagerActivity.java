package com.ztstech.vgmate.activitys.complete_org_info_v2.subview.charge_person;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ztstech.vgmate.R;
/**编辑机构责任人*/
public class EditOrgManagerActivity extends AppCompatActivity {

    public static final String ARG_NAME = "arg_name";

    public static final String ARG_PHONE = "arg_phone";

    public static final String RESULT_NAME = "result_name";

    public static final String RESULT_PHONE = "result_phone";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_org_charge_person);
    }
}
