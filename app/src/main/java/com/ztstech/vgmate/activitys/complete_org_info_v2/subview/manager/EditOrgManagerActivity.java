package com.ztstech.vgmate.activitys.complete_org_info_v2.subview.manager;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.BaseActivity;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.weigets.TopBar;

import butterknife.BindView;

/**编辑机构责任人*/
public class EditOrgManagerActivity extends BaseActivity {

    public static final String ARG_NAME = "arg_name";

    public static final String ARG_PHONE = "arg_phone";

    public static final String RESULT_NAME = "result_name";

    public static final String RESULT_PHONE = "result_phone";


    @BindView(R.id.et_name)
    EditText etName;

    @BindView(R.id.et_phone)
    EditText etPhone;

    @BindView(R.id.top_bar)
    TopBar topBar;


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_edit_org_charge_person;
    }

    @Override
    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {
        super.onViewBindFinish(savedInstanceState);

        etName.setText(getIntent().getStringExtra(ARG_NAME));
        etPhone.setText(getIntent().getStringExtra(ARG_PHONE));

        topBar.getRightTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)) {
                    ToastUtil.toastCenter(EditOrgManagerActivity.this, "请填写完整，不能填写空格");
                    return;
                }
                Intent it = new Intent();
                it.putExtra(RESULT_NAME, name);
                it.putExtra(RESULT_PHONE, phone);
                setResult(RESULT_OK, it);
                finish();
            }
        });
    }
}
