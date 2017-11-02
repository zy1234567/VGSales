package com.ztstech.vgmate.activitys.user_info.edit_info;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.ztstech.vgmate.activitys.setting.SettingActivity;
import com.ztstech.vgmate.model.fill_info.FillInfoModel;
import com.ztstech.vgmate.utils.SexUtils;
import com.ztstech.vgmate.utils.TakePhotoHelper;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.weigets.TopBar;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 编辑个人资料
 */
public class EditInfoActivity extends MVPActivity<InfoContract.Presenter> implements
        InfoContract.View, View.OnClickListener, InvokeListener, TakePhoto.TakeResultListener  {

    /**请求获取地址*/
    private static final int REQ_LOCATION = 1;

    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.img_header)
    ImageView imgHeader;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.et_id)
    EditText etId;
    @BindView(R.id.et_card_master)
    EditText etCardMaster;
    @BindView(R.id.et_card_no)
    EditText etCardNumber;
    @BindView(R.id.et_card_bank)
    EditText etBank;
    @BindView(R.id.iv_id)
    ImageView ivId;
    @BindView(R.id.iv_id_back)
    ImageView ivIdBack;
    @BindView(R.id.iv_card)
    ImageView ivCard;
    @BindView(R.id.tv_header)
    TextView tvHeader;

    /**界面数据*/
    private FillInfoModel model;

    /**当前imageView*/
    private ImageView currentImageView;


    private InvokeParam invokeParam;
    private TakePhoto takePhoto;

    /**
     * 隐私信息是否允许编辑
     */
    private boolean privateInfoEditEnabled;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_edit_info;
    }

    @Override
    protected InfoContract.Presenter initPresenter() {
        return new InfoPresenter(this);
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();

        ivCard.setOnClickListener(this);
        ivId.setOnClickListener(this);
        ivIdBack.setOnClickListener(this);
        imgHeader.setOnClickListener(this);

        tvLocation.setOnClickListener(this);
        tvBirthday.setOnClickListener(this);
        tvSex.setOnClickListener(this);


        etId.setOnClickListener(this);
        etCardMaster.setOnClickListener(this);
        etBank.setOnClickListener(this);
        etCardNumber.setOnClickListener(this);


        setEditPrivateInfoEnabled(true);

        mPresenter.loadUserModule();

        topBar.getRightTextView().setOnClickListener(this);
        topBar.requestFocus();
    }

    @Override
    protected void onSuperCreateFinish(@Nullable Bundle savedInstanceState) {
        super.onSuperCreateFinish(savedInstanceState);
        takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this)
                .bind(new TakePhotoImpl(this,this));
        takePhoto.onCreate(savedInstanceState);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        takePhoto.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_LOCATION && resultCode == RESULT_OK) {
            String locationName = data.getStringExtra(LocationSelectActivity.RESULT_NAME);
            String locationCode = data.getStringExtra(LocationSelectActivity.RESULT_CODE);

            tvLocation.setText(locationName);
            model.locationId = locationCode;
            model.location = locationName;
        }else {
            takePhoto.onActivityResult(requestCode, resultCode, data);
        }
    }

    @OnClick(R.id.tv_setting)
    public void onSettingClick(View v) {
        startActivity(new Intent(this, SettingActivity.class));
    }

    @Override
    public void onSubmitSucceed() {
        hideLoading(null);
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSubmitFailed(String message) {
        ToastUtil.toastCenter(this, "保存失败：" + message);
    }

    @Override
    public void setUserModule(FillInfoModel model) {
        this.model = model;
        setModelToView();
    }

    @Override
    public void setEditPrivateInfoEnabled(boolean enabled) {
        this.privateInfoEditEnabled = enabled;

        etId.setFocusableInTouchMode(enabled);
        etCardMaster.setFocusableInTouchMode(enabled);
        etBank.setFocusableInTouchMode(enabled);
        etCardNumber.setFocusableInTouchMode(enabled);
    }

    /**
     * 将model数据设置到界面
     */
    private void setModelToView() {
        if (this.model == null) {
            return;
        }

        if (!TextUtils.isEmpty(model.headUrl)) {
            Glide.with(this).load(model.headUrl).into(imgHeader);
            tvHeader.setText("已上传");
        }
        if (!TextUtils.isEmpty(model.cardUrl)) {
            Glide.with(this).load(model.cardUrl).into(ivCard);
        }
        if (!TextUtils.isEmpty(model.idUrl)) {
            Glide.with(this).load(model.idUrl).into(ivId);
        }
        if (!TextUtils.isEmpty(model.idBackUrl)) {
            Glide.with(this).load(model.idBackUrl).into(ivIdBack);
        }


        etBank.setText(model.cardBank);
        etCardMaster.setText(model.cardMaster);
        etCardNumber.setText(model.cardNo);
        etId.setText(model.id);
        etName.setText(model.name);
        tvLocation.setText(model.location);
        tvBirthday.setText(model.birthday);
        tvSex.setText(SexUtils.getNameById(model.sex));

    }

    @Override
    public void onClick(View view) {
        if (view == tvSex) {
            new AlertDialog.Builder(this).setTitle("选择性别").setItems(SexUtils.NAME,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (i == 0) {
                                model.sex = "01";
                            }else {
                                model.sex = "02";
                            }
                            tvSex.setText(SexUtils.getNameById(model.sex));
                        }
                    }).create().show();
        }else if (view == ivId) {
            if (!privateInfoEditEnabled) {
                showPrivateInfoDisabled();
                return;
            }
            currentImageView = (ImageView) view;
            new TakePhotoHelper(this, takePhoto, true, 56, 35).showPickDialog();
        }else if (view == ivIdBack) {
            if (!privateInfoEditEnabled) {
                showPrivateInfoDisabled();
                return;
            }
            currentImageView = (ImageView) view;
            new TakePhotoHelper(this, takePhoto, true, 56, 35).showPickDialog();
        }else if (view == ivCard) {
            if (!privateInfoEditEnabled) {
                showPrivateInfoDisabled();
                return;
            }
            currentImageView = (ImageView) view;
            new TakePhotoHelper(this, takePhoto, true, 56, 35).showPickDialog();
        }else if (view == imgHeader) {
            currentImageView = (ImageView) view;
            new TakePhotoHelper(this, takePhoto, true).showPickDialog();
        }else if (view == tvLocation) {
            startActivityForResult(new Intent(this, LocationSelectActivity.class), REQ_LOCATION);
        }else if (view == tvBirthday) {
            final DatePicker datePicker = new DatePicker(this);
            datePicker.setMaxDate(new Date().getTime());

            new AlertDialog.Builder(this).setView(datePicker)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(datePicker.getYear(), datePicker.getMonth(),
                                    datePicker.getDayOfMonth());
                            Date date = calendar.getTime();
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",
                                    Locale.getDefault());
                            String text = simpleDateFormat.format(date);
                            tvBirthday.setText(text);
                            model.birthday = text;
                        }
                    }).create().show();
        }else if (view == topBar.getRightTextView()) {
            //点击保存
            onSubmitClick();
        }else if (view == etCardNumber || view == etCardMaster || view == etId || view == etBank) {
            if (!privateInfoEditEnabled) {
                showPrivateInfoDisabled();
            }
        }

    }

    @Override
    public void takeSuccess(TResult result) {
        if (result != null && currentImageView != null) {
            String uri = result.getImage().getOriginalPath();
            File f = new File(uri);
            Glide.with(this).load(f).into(currentImageView);
            if (currentImageView == ivCard) {
                model.cardFile = f;
            }else if (currentImageView == ivIdBack) {
                model.idBackFile = f;
            }else if (currentImageView == imgHeader) {
                model.headerFile = f;
            }else if (currentImageView == ivId) {
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

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam = invokeParam;
        }
        return type;
    }

    private void onSubmitClick() {

        model.location = tvLocation.getText().toString();
        model.birthday = tvBirthday.getText().toString();
        model.cardBank = etBank.getText().toString();
        model.cardMaster = etCardMaster.getText().toString();
        model.cardNo = etCardNumber.getText().toString();
        model.id = etId.getText().toString();
        model.name = etName.getText().toString();

        if (model.location.isEmpty()) {
            ToastUtil.toastCenter(this, "请填写地址");
            return;
        }else if (model.birthday.isEmpty()) {
            ToastUtil.toastCenter(this, "请填写出生日期");
            return;
        }else if (model.cardBank.isEmpty()) {
            ToastUtil.toastCenter(this, "请填写开户银行");
            return;
        }else if (model.cardMaster.isEmpty()) {
            ToastUtil.toastCenter(this, "请填写持卡人");
            return;
        }else if (model.cardNo.isEmpty()) {
            ToastUtil.toastCenter(this, "请填写银行卡号");
            return;
        }else if (model.id.isEmpty()) {
            ToastUtil.toastCenter(this, "请填写身份证号");
            return;
        }else if (TextUtils.isEmpty(model.name)) {
            ToastUtil.toastCenter(this, "请填写姓名");
            return;
        }else if (TextUtils.isEmpty(model.sex)) {
            ToastUtil.toastCenter(this, "请填写性别");
            return;
        }else if (model.name.contains(" ")) {
            ToastUtil.toastCenter(this, "姓名不能包含空格");
            return;
        }

        mPresenter.saveInfo(model);
    }


    private void showPrivateInfoDisabled() {
        ToastUtil.toastCenter(this, "您提交的资料正在审核，审核完成后可以继续修改");
    }
}
