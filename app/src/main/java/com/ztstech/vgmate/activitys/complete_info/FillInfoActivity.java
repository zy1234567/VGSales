package com.ztstech.vgmate.activitys.complete_info;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.location_select.LocationSelectActivity;
import com.ztstech.vgmate.activitys.main.MainActivity;
import com.ztstech.vgmate.data.repository.UserRepository;
import com.ztstech.vgmate.model.fill_info.FillInfoModel;
import com.ztstech.vgmate.utils.TakePhotoHelper;
import com.ztstech.vgmate.utils.ToastUtil;

import java.io.File;

import butterknife.BindView;

/**
 * 补充信息
 */
public class FillInfoActivity extends MVPActivity<FillInfoContract.Presenter> implements
        View.OnClickListener, FillInfoContract.View, InvokeListener, TakePhoto.TakeResultListener {

    /**请求地址*/
    public static final int REQ_LOCATION = 0;

    @BindView(R.id.et_location)
    EditText etLocation;
    @BindView(R.id.et_name)
    TextInputEditText etName;
    @BindView(R.id.et_sex)
    TextInputEditText etSex;
    @BindView(R.id.et_birthday)
    TextInputEditText etBirthday;
    @BindView(R.id.et_id)
    TextInputEditText etId;
    @BindView(R.id.et_card_master)
    TextInputEditText etCardMaster;
    @BindView(R.id.et_card_no)
    TextInputEditText etCardNo;
    @BindView(R.id.et_card_bank)
    TextInputEditText etCardBank;
    @BindView(R.id.et_header)
    TextInputEditText etHeader;


    @BindView(R.id.img_header)
    ImageView imgHeader;
    @BindView(R.id.iv_id)
    ImageView imgId;
    @BindView(R.id.iv_id_back)
    ImageView imgIdBack;
    @BindView(R.id.iv_card)
    ImageView imgCard;          //银行卡

    private final FillInfoModel model = new FillInfoModel();

    private TakePhotoHelper takePhotoHelper;
    private InvokeParam invokeParam;
    private TakePhoto takePhoto;

    /**当前imageView*/
    private ImageView currentImageView;



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_LOCATION && resultCode == RESULT_OK) {
            String locationName = data.getStringExtra(LocationSelectActivity.RESULT_NAME);
            String locationCode = data.getStringExtra(LocationSelectActivity.RESULT_CODE);

            etLocation.setText(locationName);
            model.locationId = locationCode;
            model.location = locationName;
        }else {
            takePhoto.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(
                requestCode,permissions,grantResults);
        PermissionManager.handlePermissionsResult(this,type,invokeParam,this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_fill_info;
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();

        imgHeader.setOnClickListener(this);
        etHeader.setOnClickListener(this);
        imgId.setOnClickListener(this);
        imgCard.setOnClickListener(this);
        imgIdBack.setOnClickListener(this);

        etLocation.setOnClickListener(this);

    }

    @Override
    protected void onSuperCreateFinish(@Nullable Bundle savedInstanceState) {
        super.onSuperCreateFinish(savedInstanceState);
        if (mPresenter.isInfoFilled()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }
        takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this)
                .bind(new TakePhotoImpl(this,this));
        takePhoto.onCreate(savedInstanceState);

        takePhotoHelper = new TakePhotoHelper(this, takePhoto, true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        takePhoto.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }


    /**
     * 点击提交
     * @param view
     */
    public void onSubmitClick(View view) {
        onSubmitClick();
    }

    @Override
    public void onClick(View view) {
        if (view == etLocation) {
            startActivityForResult(new Intent(this, LocationSelectActivity.class), REQ_LOCATION);
        }else if (view == imgHeader || view == etHeader) {
            currentImageView = imgHeader;
            takePhotoHelper.show();
        }else if (view == imgIdBack) {
            currentImageView = imgIdBack;
            takePhotoHelper.show();
        }else if (view == imgCard) {
            currentImageView = imgCard;
            takePhotoHelper.show();
        }else if (view == imgId) {
            currentImageView = imgId;
            takePhotoHelper.show();
        }
    }

    @Override
    protected FillInfoContract.Presenter initPresenter() {
        return new FillInfoPresenter(this);
    }



    private void onSubmitClick() {
        //暂时跳转主界面
        onSubmitSucceed();

//        model.location = etLocation.getText().toString();
//        model.birthday = etBirthday.getText().toString();
//        model.cardBank = etCardBank.getText().toString();
//        model.cardMaster = etCardMaster.getText().toString();
//        model.cardNo = etCardNo.getText().toString();
//        model.id = etId.getText().toString();
//        model.name = etName.getText().toString();
//        model.sex = etSex.getText().toString();
//
//        if (model.location.isEmpty()) {
//            ToastUtil.toastCenter(this, "请填写地址");
//            return;
//        }else if (model.birthday.isEmpty()) {
//            ToastUtil.toastCenter(this, "请填写出生日期");
//            return;
//        }else if (model.cardBank.isEmpty()) {
//            ToastUtil.toastCenter(this, "请填写开户银行");
//            return;
//        }else if (model.cardMaster.isEmpty()) {
//            ToastUtil.toastCenter(this, "请填写持卡人");
//            return;
//        }else if (model.cardNo.isEmpty()) {
//            ToastUtil.toastCenter(this, "请填写银行卡号");
//            return;
//        }else if (model.id.isEmpty()) {
//            ToastUtil.toastCenter(this, "请填写身份证号");
//            return;
//        }else if (model.name.isEmpty()) {
//            ToastUtil.toastCenter(this, "请填写姓名");
//            return;
//        }else if (model.sex.isEmpty()) {
//            ToastUtil.toastCenter(this, "请填写性别");
//            return;
//        }else if (model.headerFile == null) {
//            ToastUtil.toastCenter(this, "请选择头像");
//            return;
//        }else if (model.idFile == null) {
//            ToastUtil.toastCenter(this, "请选择身份证正面照片");
//            return;
//        }else if (model.idBackFile == null) {
//            ToastUtil.toastCenter(this, "请选择身份证反面照片");
//            return;
//        }else if (model.cardFile == null) {
//            ToastUtil.toastCenter(this, "请选择银行卡照片");
//            return;
//        }
//
//        mPresenter.saveInfo(model);

    }

    @Override
    public void onSubmitSucceed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onSubmitFailed(String message) {
        ToastUtil.toastCenter(this, "" + message);
    }


    //拍照回调

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam = invokeParam;
        }
        return type;
    }

    @Override
    public void takeSuccess(TResult result) {
        if (result != null && currentImageView != null) {
            String uri = result.getImage().getOriginalPath();
            File f = new File(uri);
            Glide.with(this).load(f).into(currentImageView);
            if (currentImageView == imgCard) {
                model.cardFile = f;
            }else if (currentImageView == imgIdBack) {
                model.idBackFile = f;
            }else if (currentImageView == imgHeader) {
                model.headerFile = f;
            }else if (currentImageView == imgId) {
                model.idFile = f;
            }
        }
    }

    @Override
    public void takeFail(TResult result, String msg) {

    }

    @Override
    public void takeCancel() {

    }
}
