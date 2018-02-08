package com.ztstech.vgmate.activitys.communicate_record.add_communcate;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.ztstech.vgmate.activitys.communicate_record.com_list.CommmunicateListActivity;
import com.ztstech.vgmate.activitys.gps.GpsActivity;
import com.ztstech.vgmate.data.dto.AddComRecordData;
import com.ztstech.vgmate.utils.ContractUtils;
import com.ztstech.vgmate.utils.DialogUtils;
import com.ztstech.vgmate.utils.TakePhotoHelper;
import com.ztstech.vgmate.weigets.CustomGridView;
import com.ztstech.vgmate.weigets.DialogMultiSelect;
import com.ztstech.vgmate.weigets.TopBar;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AddComRecordActivity extends MVPActivity<AddComRecordContact.Presenter> implements InvokeListener, TakePhoto.TakeResultListener, AddComRecordContact.View {
    /**请求本次联系人*/
    public static final int REQ_CONTACT = 2;
    /**请求联系人手机*/
    public static final int REQ_CONTACT_1 = 1;
    /**02:其他人员,05:管理员,06:老板（法人）*/
    public static final String IDEN_NOMAL = "02";
    public static final String IDEN_MANAGER = "05";
    public static final String IDEN_BOSS = "06";
    /**跟进方式 01:加速跟进,02:正常跟进,03:长期跟进,04:暂不跟进*/
    public static final String FLLOW_UP_TYPE_ACCELERATE = "01";
    public static final String FLLOW_UP_TYPE_NORMAL = "02";
    public static final String FLLOW_UP_TYPE_CONYINUED= "03";
    public static final String FLLOW_UP_TYPE_NO= "04";
    /**沟通方式*/
    public static final String COMMUN_TYPE_PHONE= "00";
    public static final String COMMUN_TYPE_VISIT= "01";
    /**管理后台 00:没有,01:有'*/
    public static final String BACKSTAGE_NO= "00";
    public static final String BACKSTAGE_YES= "01";
    /**是否约下次拜访,00:否,01:是*/
    public static final String CALLON_NO= "00";
    public static final String CALLON_YES= "01";

    /**请求gps信息*/
    public static final int REQ_GPS = 3;
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
    @BindView(R.id.ck_next_visit_yes)
    CheckBox ckNextVisitYes;
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
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    private ImageView imgAddImg;

    private TakePhoto takePhoto;

    private InvokeParam invokeParam;
    /**
     * 页面数据
     */
    private AddComRecordData addComRecordData = new AddComRecordData();
    private DialogMultiSelect dialogMultiSelect;
    /**
     * 图片文件
     */
    private List<File> imageFiles = new ArrayList<>();

    private String[] datastr = {"机构法人/老板/店长","一般管理人员","其他机构人员"};
    private String rbiid;
    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        addDefaultImage();
        topBar.getRightTextView().setBackgroundResource(R.drawable.bg_c_2_f_106);
        topBar.getRightTextView().setTextSize(R.dimen.text_7);
        topBar.getRightTextView().setText("提交");
        topBar.getRightTextView().setTextColor(getResources().getColor(R.color.color_109));
        ckPhoneRecord.setEnabled(false);
        ckVsitRecord.setEnabled(false);
        ckManagerBackYes.setEnabled(false);
        ckManagerBackNo.setEnabled(false);
        ckNextVisitYes.setEnabled(false);
        ckNextVisitNo.setEnabled(false);
        etThisPhone.addTextChangedListener(new EditOnclick());
        etCall.addTextChangedListener(new EditOnclick());
        etMulPhone.addTextChangedListener(new EditOnclick());
        initData();
    }
    private void initData(){
        addComRecordData.communicationtype = COMMUN_TYPE_PHONE;
        addComRecordData.backstage = BACKSTAGE_YES;
        addComRecordData.callon = CALLON_YES;
        rbiid = getIntent().getStringExtra(CommmunicateListActivity.ARG_RBIID);
        addComRecordData.rbiid = rbiid;
    }
    @Override
    protected void onSuperCreateFinish(@Nullable Bundle savedInstanceState) {
        super.onSuperCreateFinish(savedInstanceState);
        takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this)
                .bind(new TakePhotoImpl(this, this));
        takePhoto.onCreate(savedInstanceState);

    }

    @Override
    protected AddComRecordContact.Presenter initPresenter() {
        return new AddComRecordPresenter(this);
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
    public void takeFail(TResult result, String msg) {
    }

    @Override
    public void takeCancel() {
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(
                TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
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
        if (requestCode == REQ_CONTACT && resultCode == RESULT_OK) {
            //得到本次联系人
            ContractUtils.ContractUser user = ContractUtils.readContract(this, data);
            etThisPhone.setText(user.phone);
            addComRecordData.makecall = user.phone;
            btcommitstate();
        }else if (requestCode == REQ_CONTACT_1 && resultCode == RESULT_OK){
            //得到联系人
            ContractUtils.ContractUser user = ContractUtils.readContract(this, data);
            etTheOpPhone.setText(user.phone);
            addComRecordData.contactsphone = user.phone;
        }else if (requestCode == REQ_GPS && resultCode == RESULT_OK){
            // 获取经纬度
            double la = data.getDoubleExtra(GpsActivity.RESULT_LATITUDE, 0);
            double lo = data.getDoubleExtra(GpsActivity.RESULT_LONGITUDE, 0);
            String location = data.getStringExtra(GpsActivity.RESULT_LOCATION);
            // 经度在前纬度在后
            tvFieldLocation.setText(lo + "," + la);
            tvFieldLocation.setTag(lo + "," + la);
            addComRecordData.spotgps = tvFieldLocation.getText().toString();
        }
        takePhoto.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 增加一张图片
     *
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

    @Override
    public File[] getImgaeFiles() {
        File[] files = new File[imageFiles.size()];
        for (int i = 0; i < imageFiles.size(); i++) {
            files[i] = imageFiles.get(i);
        }
        return files;
    }

    @Override
    public void onSubmitFinish(String errorMessage) {
        if (errorMessage == null) {
            finish();
        }else {
            Toast.makeText(this, "添加失败：" + errorMessage, Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick({R.id.tv_commit,R.id.ll_phone_record, R.id.ll_vsit_record, R.id.tv_phone_mail_this, R.id.tv_phone_mail_the_op, R.id.ll_manager_back_yes, R.id.ll_manager_back_no, R.id.ll_next_visit_yes, R.id.ll_next_visit_no, R.id.tv_follow_up, R.id.tv_field_location,
            R.id.tv_position})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_commit:
                checkToSubmit();
                break;
            case R.id.ll_phone_record:
                rlThisPhone.setVisibility(View.VISIBLE);
                ckVsitRecord.setChecked(false);
                ckPhoneRecord.setChecked(true);
                addComRecordData.communicationtype = COMMUN_TYPE_PHONE;
                break;
            case R.id.ll_vsit_record:
                rlThisPhone.setVisibility(View.GONE);
                ckPhoneRecord.setChecked(false);
                ckVsitRecord.setChecked(true);
                etThisPhone.setText("");
                addComRecordData.communicationtype = COMMUN_TYPE_VISIT;
                break;
            case R.id.tv_phone_mail_this:
                //本次拨打联系人
                ContractUtils.toContract(this, REQ_CONTACT);
                break;
            case R.id.tv_phone_mail_the_op:
                //联系人手机
                ContractUtils.toContract(this, REQ_CONTACT_1);
                break;
            case R.id.ll_manager_back_yes:
                ckManagerBackYes.setChecked(true);
                ckManagerBackNo.setChecked(false);
                addComRecordData.backstage = BACKSTAGE_YES;
                break;
            case R.id.ll_manager_back_no:
                ckManagerBackYes.setChecked(false);
                ckManagerBackNo.setChecked(true);
                addComRecordData.backstage = BACKSTAGE_NO;
                break;
            case R.id.ll_next_visit_yes:
                ckNextVisitYes.setChecked(true);
                ckNextVisitNo.setChecked(false);
                addComRecordData.callon = CALLON_YES;
                break;
            case R.id.ll_next_visit_no:
                ckNextVisitYes.setChecked(false);
                ckNextVisitNo.setChecked(true);
                addComRecordData.callon = CALLON_NO;
                break;
            case R.id.tv_follow_up:
                showFllowUpDialog();
                break;
            case R.id.tv_field_location:
                Intent it = new Intent(this, GpsActivity.class);
                startActivityForResult(it, REQ_GPS);
                break;
            case R.id.tv_position:
                showSelectDialog();
                break;
            default:
                break;
        }
    }
    /**
     * 检测提交
     */
    private void checkToSubmit() {

        addComRecordData.contactsname = etCall.getText().toString();
        addComRecordData.makecall = etThisPhone.getText().toString();
        addComRecordData.msg =  etCommunicate.getText().toString();
        addComRecordData.consultphone = etMulPhone.getText().toString();
        if (ckPhoneRecord.isChecked()){
            if (TextUtils.isEmpty(etThisPhone.getText().toString()) || TextUtils.isEmpty(etCall.getText().toString()) || TextUtils.isEmpty(tvPosition.getText().toString())
                    || TextUtils.isEmpty(etMulPhone.getText().toString())){
                return;
            }
        }else{
            if (TextUtils.isEmpty(etCall.getText().toString()) || TextUtils.isEmpty(tvPosition.getText().toString())
                    || TextUtils.isEmpty(etMulPhone.getText().toString())){
                return;
            }
        }


        mPresenter.submit(addComRecordData);
    }
    /**
     * 判断提交按钮状态
     */
    private void btcommitstate(){
        String etthisphonestr = etThisPhone.getText().toString();
        String etcallstr = etCall.getText().toString();
        String etmulphonestr = etMulPhone.getText().toString();
        if (ckPhoneRecord.isChecked()){
            if ((!TextUtils.isEmpty(etthisphonestr) && etthisphonestr.length() == 11) && !TextUtils.isEmpty(etcallstr) && !TextUtils.isEmpty(tvPosition.getText().toString()) &&
                    !TextUtils.isEmpty(etmulphonestr)){
                tvCommit.setBackgroundResource(R.drawable.bg_c_2_f_001);
            }else{
                tvCommit.setBackgroundResource(R.drawable.bg_c_2_f_106);
            }
        }else{
            if (!TextUtils.isEmpty(etcallstr) && !TextUtils.isEmpty(tvPosition.getText().toString()) &&
                    !TextUtils.isEmpty(etmulphonestr)){
                tvCommit.setBackgroundResource(R.drawable.bg_c_2_f_001);
            }else{
                tvCommit.setBackgroundResource(R.drawable.bg_c_2_f_106);
            }
        }
    }

    /**
     * 选择担任职位
     */
    private void showSelectDialog(){
        if (dialogMultiSelect == null){
            dialogMultiSelect = new DialogMultiSelect(this, datastr, new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    tvPosition.setText(datastr[i]);
                    dialogMultiSelect.dismiss();
                    switch (i){
                        case 0:
                            addComRecordData.roletype = IDEN_BOSS;
                            break;
                        case 1:
                            addComRecordData.roletype = IDEN_MANAGER;
                            break;
                        case 2:
                            addComRecordData.roletype = IDEN_NOMAL;
                            break;
                        default:
                            break;
                    }
                    btcommitstate();
                }
            });
        }
        dialogMultiSelect.show();
    }
    /**
     * 跟进方式dialog
     */
    private void showFllowUpDialog(){
        DialogUtils.showFllowUpDialog(this, new DialogUtils.showFllowUpCallBack() {
            @Override
            public void onFllowUpClick(int position) {
                switch (position){
                    case 0:
                        tvFollowUp.setText("加速跟进");
                        addComRecordData.followtype = FLLOW_UP_TYPE_ACCELERATE;
                        break;
                    case 1:
                        tvFollowUp.setText("正常跟进");
                        addComRecordData.followtype = FLLOW_UP_TYPE_NORMAL;
                        break;
                    case 2:
                        tvFollowUp.setText("长期跟进");
                        addComRecordData.followtype = FLLOW_UP_TYPE_CONYINUED;
                        break;
                    case 3:
                        tvFollowUp.setText("暂不跟进");
                        addComRecordData.followtype = FLLOW_UP_TYPE_NO;
                        break;
                    default:
                        break;
                }
            }
        });
    }
    private class EditOnclick implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            btcommitstate();
        }
    }
}
