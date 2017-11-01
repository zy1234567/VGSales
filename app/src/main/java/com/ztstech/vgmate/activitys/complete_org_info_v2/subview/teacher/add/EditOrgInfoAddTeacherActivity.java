package com.ztstech.vgmate.activitys.complete_org_info_v2.subview.teacher.add;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jph.takephoto.model.TResult;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.complete_org_info_v2.subview.org_logo.EditOrgLogoActivity;
import com.ztstech.vgmate.base.BaseActivity;
import com.ztstech.vgmate.data.beans.TeacherListBean;
import com.ztstech.vgmate.data.dto.AddTeacherData;
import com.ztstech.vgmate.utils.TakePhotoHelperWapper;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.weigets.TopBar;

import java.io.File;

import butterknife.BindView;

/**
 * 添加教师界面
 */
public class EditOrgInfoAddTeacherActivity extends MVPActivity<EditOrgInfoAddTeacherContract.Presenter>
        implements EditOrgInfoAddTeacherContract.View {

    /**
     * 传入医师信息
     */
    public static final String ARG_TEACHER_INFO = "arg_teacher_info";

    /**
     * 传入信息
     */
    public static final String RESULT_INFO = "result_info";

    private TeacherListBean.ListBean bean;


    @BindView(R.id.img_header)
    ImageView imgHeader;

    @BindView(R.id.et_name)
    EditText tvName;

    @BindView(R.id.tv_class)
    TextView tvClass;

    @BindView(R.id.top_bar)
    TopBar topBar;

    @BindView(R.id.tv_age)
    TextView tvAge;

    @BindView(R.id.tv_sex)
    TextView tvSex;

    @BindView(R.id.et_desc)
    EditText etDesc;


    /**是否为编辑模式*/
    private boolean edit;

    /**提交数据*/
    private final AddTeacherData addTeacherData = new AddTeacherData();

    private TakePhotoHelperWapper takePhotoHelperWapper;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_edit_org_info_add_teacher;
    }

    @Override
    protected void onViewBindFinish(@Nullable Bundle savedInstanceState) {
        super.onViewBindFinish(savedInstanceState);

        takePhotoHelperWapper = new TakePhotoHelperWapper(this) {
            @Override
            public void takeSuccess(TResult result) {
                String uri = result.getImage().getOriginalPath();
                final File f = new File(uri);

                Glide.with(EditOrgInfoAddTeacherActivity.this).load(f).into(imgHeader);
            }
        };

        takePhotoHelperWapper.onCreate(savedInstanceState);

        final String data = getIntent().getStringExtra(ARG_TEACHER_INFO);
        if (!TextUtils.isEmpty(data)) {
            this.bean = new Gson().fromJson(data, TeacherListBean.ListBean.class);
            tvName.setText(bean.name);
            tvClass.setText(bean.position);
            Glide.with(this).load(bean.logosurl).into(imgHeader);

            edit = false;
        }else {
            edit = true;
        }

        topBar.getRightTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击保存
                submit();
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        takePhotoHelperWapper.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        takePhotoHelperWapper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected EditOrgInfoAddTeacherContract.Presenter initPresenter() {
        return new EditOrgInfoAddTeacherPresenter(this);
    }

    @Override
    public void submitFinish(@Nullable String errmsg) {
        if (errmsg != null) {
            ToastUtil.toastCenter(this, "添加教师失败：" + errmsg);
        }
    }

    private void submit() {


        mPresenter.submit(addTeacherData);

    }
}
