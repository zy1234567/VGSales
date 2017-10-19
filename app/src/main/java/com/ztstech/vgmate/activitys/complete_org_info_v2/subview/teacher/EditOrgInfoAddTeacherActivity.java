package com.ztstech.vgmate.activitys.complete_org_info_v2.subview.teacher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.base.BaseActivity;
import com.ztstech.vgmate.data.beans.TeacherListBean;

import butterknife.BindView;

/**
 * 添加教师界面
 */
public class EditOrgInfoAddTeacherActivity extends BaseActivity {

    /**
     * 传入医师信息
     */
    public static final String ARG_TEACHER_INFO = "arg_teacher_info";

    private TeacherListBean.ListBean bean;


    @BindView(R.id.img_header)
    ImageView imgHeader;

    @BindView(R.id.et_name)
    EditText tvName;

    @BindView(R.id.et_class)
    EditText tvClass;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_edit_org_info_add_teacher;
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();

        String data = getIntent().getStringExtra(ARG_TEACHER_INFO);
        if (!TextUtils.isEmpty(data)) {
            this.bean = new Gson().fromJson(data, TeacherListBean.ListBean.class);
            tvName.setText(bean.name);
            tvClass.setText(bean.position);
            Glide.with(this).load(bean.logosurl).into(imgHeader);
        }


    }
}
