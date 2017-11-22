package com.ztstech.vgmate.activitys.user_info.edit_info;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.ztstech.appdomain.constants.Constants;
import com.ztstech.appdomain.repository.UserRepository;
import com.ztstech.appdomain.user_case.ApproveMate;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.location_select.LocationSelectActivity;
import com.ztstech.vgmate.activitys.photo_browser.PhotoBrowserActivity;
import com.ztstech.vgmate.activitys.setting.SettingActivity;
import com.ztstech.vgmate.data.beans.UnApproveMateBean;
import com.ztstech.vgmate.model.fill_info.FillInfoModel;
import com.ztstech.vgmate.utils.CommonUtil;
import com.ztstech.vgmate.utils.SexUtils;
import com.ztstech.vgmate.utils.TakePhotoHelper;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.weigets.IOSStyleDialog;
import com.ztstech.vgmate.weigets.TopBar;
import com.ztstech.vgmate.weigets.dateTimePicker.DatePickerDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 编辑个人资料和审批销售界面用的同一界面
 */
public class EditInfoActivity extends MVPActivity<InfoContract.Presenter> implements
        InfoContract.View, View.OnClickListener, InvokeListener, TakePhoto.TakeResultListener {

    /**
     * 请求获取地址
     */
    private static final int REQ_LOCATION = 1;

    /**
     * 用来判断判断要显示的是审批界面还是编辑自己资料界面的两个value和传值key
     */
    public static final String FROM_EDIT_SELF = "from_edit_self";
    public static final String FROM_APPROVE_MATE = "from_approve_mate";

    public static final String SHOW_TYPE = "show_type";
    public static final String KEY_BEAN = "bean";
    public static final String KEY_SALEID = "sale_id";

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
    @BindView(R.id.tv_pass)
    TextView tvPass;
    @BindView(R.id.tv_refuse)
    TextView tvRefuse;
    @BindView(R.id.ll_approve)
    LinearLayout llApprove;

    /**
     * 界面数据
     */
    private FillInfoModel model;

    /**
     * 当前imageView
     */
    private ImageView currentImageView;


    private InvokeParam invokeParam;
    private TakePhoto takePhoto;

    /**
     * 年龄选择dialog
     */
    private Dialog birthPickerDialog;

    /**
     * 年龄选择当前年
     */
    private int mCurYear;
    /**
     * 当前月M
     */
    private int mCurMon;
    /**
     * 当前日
     */
    private int mCurDay;

    /**
     * 隐私信息是否允许编辑
     */
    private boolean privateInfoEditEnabled;

    /**
     * 用来判断判断要显示的是审批界面还是编辑自己资料界面
     */
    private String showType;

    /**
     * 如果是销售审批界面传过来的bean
     */
    private UnApproveMateBean bean;

    /**
     * 是否是销售审批界面
     */
    private boolean mateApproveflg;

    /**
     * 如果是销售审批界面传过来的销售id
     */
    private String saleid;

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
        showType = getIntent().getStringExtra(SHOW_TYPE);
        bean = (UnApproveMateBean) getIntent().getSerializableExtra(KEY_BEAN);
        saleid = getIntent().getStringExtra(KEY_SALEID);
        mateApproveflg = TextUtils.equals(showType,FROM_APPROVE_MATE);
        ivCard.setOnClickListener(this);
        ivId.setOnClickListener(this);
        ivIdBack.setOnClickListener(this);
        tvPass.setOnClickListener(this);
        tvRefuse.setOnClickListener(this);


        if (mateApproveflg){
            // 如果是审批销售界面
            topBar.getRightTextView().setVisibility(View.GONE);
            llApprove.setVisibility(View.VISIBLE);
            mPresenter.loadMateModule(bean);
            setEditPrivateInfoEnabled(false);
            topBar.setTitle("销售审批");
        }else {
            // 如果是完善自己资料界面
            topBar.getRightTextView().setVisibility(View.VISIBLE);
            llApprove.setVisibility(View.GONE);
            mPresenter.loadUserModule();
            imgHeader.setOnClickListener(this);

            tvLocation.setOnClickListener(this);
            tvBirthday.setOnClickListener(this);
            tvSex.setOnClickListener(this);

            etId.setOnClickListener(this);
            etCardMaster.setOnClickListener(this);
            etBank.setOnClickListener(this);
            etCardNumber.setOnClickListener(this);
            if (!Constants.USER_ID_CHECKING.equals(UserRepository.getInstance().getUser().getUserBean().info.status)){
                setEditPrivateInfoEnabled(true);
            }
        }


        topBar.getRightTextView().setOnClickListener(this);
        topBar.requestFocus();
    }

    @Override
    protected void onSuperCreateFinish(@Nullable Bundle savedInstanceState) {
        super.onSuperCreateFinish(savedInstanceState);
        takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this)
                .bind(new TakePhotoImpl(this, this));
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
        } else {
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
        finish();
    }

    @Override
    public void onSubmitFailed(String message) {
        ToastUtil.toastCenter(this, "保存失败：" + message);
    }

    @Override
    public void setUserModule(FillInfoModel model) {
        this.model = model;
        setUserModelToView();
    }

    @Override
    public void setEditPrivateInfoEnabled(boolean enabled) {
        this.privateInfoEditEnabled = enabled;
        etName.setFocusableInTouchMode(enabled);
        etId.setFocusableInTouchMode(enabled);
        etCardMaster.setFocusableInTouchMode(enabled);
        etBank.setFocusableInTouchMode(enabled);
        etCardNumber.setFocusableInTouchMode(enabled);
    }

    @Override
    public void showError(String errmsg) {
        ToastUtil.toastCenter(this,errmsg);
    }

    @Override
    public void onApproveSucceed() {
        ToastUtil.toastCenter(this,"操作成功!");
        finish();
    }


    /**
     * 将model数据设置到界面
     */
    private void setUserModelToView() {
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
        if (model.birthday != null) {
            String[] birthArray = model.birthday.split("-");
            mCurYear = Integer.valueOf(birthArray[0]);
            mCurMon = Integer.valueOf(birthArray[1]);
            mCurDay = Integer.valueOf(birthArray[2]);
        }
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
                            } else {
                                model.sex = "02";
                            }
                            tvSex.setText(SexUtils.getNameById(model.sex));
                        }
                    }).create().show();
        } else if (view == ivId) {
            if (mateApproveflg){
                toPhotoBrowser(0);
                return;
            }
            if (!privateInfoEditEnabled) {
                showPrivateInfoDisabled();
                return;
            }
            currentImageView = (ImageView) view;
            new TakePhotoHelper(this, takePhoto, false).showPickDialog();
        } else if (view == ivIdBack) {
            if (mateApproveflg){
                toPhotoBrowser(1);
                return;
            }
            if (!privateInfoEditEnabled) {
                showPrivateInfoDisabled();
                return;
            }
            currentImageView = (ImageView) view;
            new TakePhotoHelper(this, takePhoto, false).showPickDialog();
        } else if (view == ivCard) {
            if (mateApproveflg){
                toPhotoBrowser(2);
                return;
            }
            if (!privateInfoEditEnabled) {
                showPrivateInfoDisabled();
                return;
            }
            currentImageView = (ImageView) view;
            new TakePhotoHelper(this, takePhoto, false).showPickDialog();
        } else if (view == imgHeader) {
            currentImageView = (ImageView) view;
            new TakePhotoHelper(this, takePhoto, true).showPickDialog();
        } else if (view == tvLocation) {
            startActivityForResult(new Intent(this, LocationSelectActivity.class), REQ_LOCATION);
        } else if (view == tvBirthday) {
            DatePickerDialog.Builder builder = new DatePickerDialog.Builder(this);
            birthPickerDialog = builder.setSelectYear(mCurYear - 1).setSelectMonth(mCurMon - 1)
                    .setSelectDay(mCurDay - 1).
                            setMaxYear(2017).setMaxMonth(12).setMaxDay(31).
                            setMinYear(1920).setMinMonth(1).setMinDay(1).setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
                        @Override
                        public void onDateSelected(int[] dates) {
                            mCurYear = dates[0];
                            mCurMon = dates[1];
                            mCurDay = dates[2];
                            String result = mCurYear + "-" + CommonUtil.handleZero(mCurMon + "") + "-" + CommonUtil.handleZero(mCurDay + "");
                            tvBirthday.setText(result);
                            model.birthday = result;
                        }

                        @Override
                        public void onCancel() {
                        }
                    }).create();
            birthPickerDialog.show();
        } else if (view == topBar.getRightTextView()) {
            //点击保存
            onSubmitClick();
        } else if (view == etCardNumber || view == etCardMaster || view == etId || view == etBank) {
            if (!privateInfoEditEnabled) {
                showPrivateInfoDisabled();
            }
        }else if (view == tvPass){
            //通过
            new IOSStyleDialog(this, "您确定要通过吗？", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mPresenter.approveMate(saleid, ApproveMate.STATUS_PASS);
                }
            }).show();

        }else if (view == tvRefuse){
            //拒绝
            new IOSStyleDialog(this, "您确定要拒绝吗？", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mPresenter.approveMate(saleid, ApproveMate.STATUS_REFUSE);
                }
            }).show();
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
            } else if (currentImageView == ivIdBack) {
                model.idBackFile = f;
            } else if (currentImageView == imgHeader) {
                model.headerFile = f;
            } else if (currentImageView == ivId) {
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
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
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
        } else if (model.birthday.isEmpty()) {
            ToastUtil.toastCenter(this, "请填写出生日期");
            return;
        } else if (model.cardBank.isEmpty()) {
            ToastUtil.toastCenter(this, "请填写开户银行");
            return;
        } else if (model.cardMaster.isEmpty()) {
            ToastUtil.toastCenter(this, "请填写持卡人");
            return;
        } else if (model.cardNo.isEmpty()) {
            ToastUtil.toastCenter(this, "请填写银行卡号");
            return;
        } else if (model.id.isEmpty()) {
            ToastUtil.toastCenter(this, "请填写身份证号");
            return;
        } else if (TextUtils.isEmpty(model.name)) {
            ToastUtil.toastCenter(this, "请填写姓名");
            return;
        } else if (TextUtils.isEmpty(model.sex)) {
            ToastUtil.toastCenter(this, "请填写性别");
            return;
        } else if (model.name.contains(" ")) {
            ToastUtil.toastCenter(this, "姓名不能包含空格");
            return;
        }

        mPresenter.saveInfo(model);
    }


    private void showPrivateInfoDisabled() {
        ToastUtil.toastCenter(this, "您提交的资料正在审核，审核完成后可以继续修改");
    }

    /**
     * 跳转至图片浏览器界面查看身份证和银行卡
     */
    private void toPhotoBrowser(int postiion){
        List<String> list = new ArrayList<>();
        list.add(model.idUrl);
        list.add(model.idBackUrl);
        list.add(model.cardUrl);
        Intent intent = new Intent(this, PhotoBrowserActivity.class);
        intent.putExtra(PhotoBrowserActivity.KEY_POSITION,postiion);
        intent.putStringArrayListExtra(PhotoBrowserActivity.KEY_LIST, (ArrayList<String>) list);
        startActivity(intent);
    }

}
