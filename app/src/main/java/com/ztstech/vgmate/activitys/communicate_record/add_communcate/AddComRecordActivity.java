package com.ztstech.vgmate.activitys.communicate_record.add_communcate;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.utils.TakePhotoHelper;
import com.ztstech.vgmate.weigets.CustomGridView;
import com.ztstech.vgmate.weigets.TopBar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddComRecordActivity extends MVPActivity implements InvokeListener, TakePhoto.TakeResultListener{

    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.ck_phone_record)
    CheckBox ckPhoneRecord;
    @BindView(R.id.ll_phone_record)
    LinearLayout llPhoneRecord;
    @BindView(R.id.ck_vsit_record)
    CheckBox ckVsitRecord;
    @BindView(R.id.ll_vsit_record)
    LinearLayout llVsitRecord;
    @BindView(R.id.et_this_phone)
    EditText etThisPhone;
    @BindView(R.id.tv_phone_mail_this)
    TextView tvPhoneMailThis;
    @BindView(R.id.rl_this_phone)
    RelativeLayout rlThisPhone;
    @BindView(R.id.et_call)
    EditText etCall;
    @BindView(R.id.et_the_op_phone)
    EditText etTheOpPhone;
    @BindView(R.id.tv_phone_mail_the_op)
    TextView tvPhoneMailTheOp;
    @BindView(R.id.tv_position)
    TextView tvPosition;
    @BindView(R.id.et_mul_phone)
    EditText etMulPhone;
    @BindView(R.id.ck_manager_back_yes)
    CheckBox ckManagerBackYes;
    @BindView(R.id.ll_manager_back_yes)
    LinearLayout llManagerBackYes;
    @BindView(R.id.ck_manager_back_no)
    CheckBox ckManagerBackNo;
    @BindView(R.id.ll_manager_back_no)
    LinearLayout llManagerBackNo;
    @BindView(R.id.ck_yes)
    CheckBox ckYes;
    @BindView(R.id.ll_next_visit_yes)
    LinearLayout llNextVisitYes;
    @BindView(R.id.ck_next_visit_no)
    CheckBox ckNextVisitNo;
    @BindView(R.id.ll_next_visit_no)
    LinearLayout llNextVisitNo;
    @BindView(R.id.et_communicate)
    EditText etCommunicate;
    @BindView(R.id.tv_follow_up)
    TextView tvFollowUp;
    @BindView(R.id.tv_field_location)
    TextView tvFieldLocation;
    @BindView(R.id.ll_field_location)
    LinearLayout llFieldLocation;
    @BindView(R.id.cgv)
    CustomGridView customGridView;
    private ImageView imgAddImg;

    private TakePhoto takePhoto;

    private InvokeParam invokeParam;

    /**
     * 图片文件
     */
    private List<File> imageFiles = new ArrayList<>();

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        addDefaultImage();
    }

    @Override
    protected void onSuperCreateFinish(@Nullable Bundle savedInstanceState) {
        super.onSuperCreateFinish(savedInstanceState);
        takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this)
                .bind(new TakePhotoImpl(this,this));
        takePhoto.onCreate(savedInstanceState);
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_add_com_record;
    }

    /**
     * 显示选取图片
     */
    private void showPickImage() {
        new TakePhotoHelper(this, takePhoto, false).showPickDialog();
    }

    /**
     * 增加底部添加图片图片
     */
    private void addDefaultImage() {
        imgAddImg = new ImageView(this);
        imgAddImg.setImageResource(R.mipmap.add_img);
        imgAddImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imgAddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPickImage();
            }
        });
        customGridView.addView(imgAddImg);
        customGridView.requestLayout();
    }

    @Override
    public void takeSuccess(TResult result) {
        if (result != null) {
            String uri = result.getImage().getOriginalPath();
            final File f = new File(uri);
            addImage(f);
        }
    }

    @Override
    public void takeFail(TResult result, String msg) {}

    @Override
    public void takeCancel() {}

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(
                TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam = invokeParam;
        }
        return type;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        takePhoto.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 增加一张图片
     * @param f
     */
    private void addImage(final File f) {
        final View itemView = LayoutInflater.from(this).inflate(R.layout.item_img_with_del,
                customGridView, false);
        ImageView img = itemView.findViewById(R.id.img);
        Glide.with(this).load(f).into(img);
        View del = itemView.findViewById(R.id.del);
        final TextView tvAddDesc = itemView.findViewById(R.id.tv_desc);
        tvAddDesc.setVisibility(View.GONE);

        imageFiles.add(0, f);
        customGridView.addView(itemView, 0);

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customGridView.removeView(itemView);
                imageFiles.remove(f);
            }
        });
    }
}
