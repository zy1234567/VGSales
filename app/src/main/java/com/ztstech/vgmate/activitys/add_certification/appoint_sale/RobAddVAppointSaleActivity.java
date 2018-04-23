package com.ztstech.vgmate.activitys.add_certification.appoint_sale;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.add_certification.RobAddVCertificationActivity;
import com.ztstech.vgmate.activitys.gps.GpsActivity;
import com.ztstech.vgmate.activitys.rob_chance.rob_ing.RobIngActivty;
import com.ztstech.vgmate.data.beans.RobChanceBean;
import com.ztstech.vgmate.data.dto.OrgPassData;
import com.ztstech.vgmate.event.ApproveEvent;
import com.ztstech.vgmate.manager.MatissePhotoHelper;
import com.ztstech.vgmate.matisse.Matisse;
import com.ztstech.vgmate.matisse.MimeType;
import com.ztstech.vgmate.utils.ToastUtil;
import com.ztstech.vgmate.weigets.CustomGridView;
import com.ztstech.vgmate.weigets.TopBar;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ztstech.vgmate.activitys.rob_chance.rob_ing.RobIngActivty.ORG_BEAN_ROB;

/**
 * Created by Administrator on 2018/4/23.
 */

public class RobAddVAppointSaleActivity extends MVPActivity<RobAddVAppointSaleContract.Presenter>
        implements RobAddVAppointSaleContract.View {


    @BindView(R.id.top_bar)
    TopBar topBar;
    @BindView(R.id.tv_way)
    TextView tvWay;
    @BindView(R.id.rl_top)
    LinearLayout rlTop;
    @BindView(R.id.rb_remote)
    RadioButton rbRemote;
    @BindView(R.id.rb_shop)
    RadioButton rbShop;
    @BindView(R.id.rg_button)
    RadioGroup rgButton;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.ll_wx)
    LinearLayout llWx;
    @BindView(R.id.tv_1)
    TextView tv1;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.v1)
    View v1;
    @BindView(R.id.img_vido)
    ImageView imgVido;
    @BindView(R.id.img_location)
    ImageView imgLocation;
    @BindView(R.id.rl_img)
    RelativeLayout rlImg;
    @BindView(R.id.rl_remote)
    RelativeLayout rlRemote;
    @BindView(R.id.rl_photo)
    RelativeLayout rlPhoto;
    @BindView(R.id.rl_dismiss)
    RelativeLayout rlDismiss;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.rl_way_shop)
    LinearLayout rlWayShop;
    @BindView(R.id.ll_center)
    RelativeLayout llCenter;
    @BindView(R.id.ll_more)
    LinearLayout llMore;
    @BindView(R.id.tv_more)
    EditText tvMore;
//    @BindView(R.id.tv_more_count)
//    TextView tvMoreCount;
    @BindView(R.id.v2)
    View v2;
    @BindView(R.id.ll_reminder)
    LinearLayout llReminder;
    @BindView(R.id.ll_buttom)
    LinearLayout llButtom;
    @BindView(R.id.tv_pass)
    TextView tvPass;
    @BindView(R.id.gv_img)
    CustomGridView customGridView;
    ImageView imgAddImg;
     final int REQUESTCODEVIDIO=11;
     final int REQUSTCODELOCATION=12;
     final int REQUSTCODEPOSITION=13;
    RobChanceBean.ListBean bean;
    /**
     * 请求gps信息
     */
    public static final int REQ_GPS = 3;
     File[] files;
     private OrgPassData orgPassData = new OrgPassData();
    /**图片文件地址*/
    private List<String> imageFiles = new ArrayList<>();
    public static String RBIID;
    String rbiid;
    int MAX_PIC_COUNT=5;

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        bean = new Gson().fromJson(getIntent().getStringExtra(ORG_BEAN_ROB), RobChanceBean.ListBean.class);
        initView();
        addDefaultImage();
    }
    private void initView() {
        rbRemote.setChecked(true);
        rlWayShop.setVisibility(View.GONE);
        llReminder.setVisibility(View.VISIBLE);
        tvPass.setBackgroundResource(R.drawable.bg_c_2_f_009);
        rgButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.rb_remote){
                    rlWayShop.setVisibility(View.GONE);
                    llReminder.setVisibility(View.VISIBLE);
                    rlRemote.setVisibility(View.VISIBLE);
                    tvPass.setBackgroundResource(R.drawable.bg_c_2_f_009);

                }else {
                    rlWayShop.setVisibility(View.VISIBLE);
                    llReminder.setVisibility(View.GONE);
                    rlRemote.setVisibility(View.GONE);
                }
            }
        });
    }
    /**
     * 增加默认图片
     */
    private void addDefaultImage() {
        imgAddImg = new ImageView(this);
        imgAddImg.setImageResource(R.mipmap.add_img);
        imgAddImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imgAddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == imgAddImg) {
                    MatissePhotoHelper.selectPhoto(RobAddVAppointSaleActivity.this,
                            MAX_PIC_COUNT - imageFiles.size(), REQUSTCODEPOSITION,MimeType.ofImage());
                }
            }
        });
        customGridView.addView(imgAddImg);
        customGridView.requestLayout();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUESTCODEVIDIO&&resultCode==RESULT_OK){
            addImg(imgVido,data,1);
        }else  if(requestCode==REQUSTCODELOCATION&&resultCode==RESULT_OK){
            addImg(imgLocation,data,0);
        } else if(requestCode==REQUSTCODEPOSITION&&resultCode==RESULT_OK){
            for (int i = 0; i < Matisse.obtainPathResult(data).size(); i++){
                addItem(Matisse.obtainPathResult(data).get(i),null);
            }
        }else if (requestCode == REQ_GPS && resultCode == RESULT_OK) {
            // 获取经纬度
            double la = data.getDoubleExtra(GpsActivity.RESULT_LATITUDE, 0);
            double lo = data.getDoubleExtra(GpsActivity.RESULT_LONGITUDE, 0);
            String location = data.getStringExtra(GpsActivity.RESULT_LOCATION);
            // 经度在前纬度在后
            tvLocation.setText(lo + "," + la);
            tvLocation.setTag(lo + "," + la);
        }
    }
    private void addItem(final String imgPath, String desc) {
        final View itemView = LayoutInflater.from(this)
                .inflate(R.layout.item_img_with_del,
                        customGridView, false);
        ImageView img = itemView.findViewById(R.id.img);
        Glide.with(this).load(imgPath).into(img);
        View del = itemView.findViewById(R.id.del);
        final TextView tvAddDesc = itemView.findViewById(R.id.tv_desc);
        tvAddDesc.setVisibility(View.GONE);
        if (!TextUtils.isEmpty(desc)) {
            tvAddDesc.setText(desc);
            tvAddDesc.setTag(desc);
        }
        imageFiles.add(imgPath);
        customGridView.addView(itemView, 0);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customGridView.removeView(itemView);
                imageFiles.remove(imgPath);
             //   buttontype();
            }
        });
    }
    private void addImg(ImageView img,Intent data,int type) {
        for (int i = 0; i < Matisse.obtainPathResult(data).size(); i++){
            files = new File[1];
            String uri =Matisse.obtainPathResult(data).get(i);
            final File f = new File(uri);
            Glide.with(RobAddVAppointSaleActivity.this).
                    load(uri).placeholder(R.mipmap.pre_default_image).
                    error(R.mipmap.pre_default_image).into(img);
            files[0] = f;
            mPresenter.uploadimg(orgPassData,type);
        }
    }
    @OnClick({R.id.ll_buttom, R.id.img_vido, R.id.img_location,R.id.tv_location})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_buttom:
                Commit();
                break;
            case R.id.img_vido:
                choosePicture(1,REQUESTCODEVIDIO);
                break;
            case R.id.img_location:
                choosePicture(1,REQUSTCODELOCATION);
                break;
            case R.id.tv_location:
                Intent it = new Intent(this, GpsActivity.class);
                startActivityForResult(it, REQ_GPS);
            break;
            default:
                break;
        }
    }
    private  void choosePicture(int maxLength,int requstcode){
        MatissePhotoHelper.selectPhoto(this,
                maxLength , requstcode,MimeType.ofImage());
    }
    private  void  Commit(){
       // orgPassData.approvetype
        orgPassData.rbiid=String.valueOf(bean.rbiid);
        orgPassData.rbiostatus="00";
        orgPassData.type="01";
        orgPassData.terminal="02";
        orgPassData.calid=bean.calid;
        if(rbRemote.isChecked()){
           if(!tvName.getText().toString().equals("")){
               orgPassData.wechatid=tvName.getText().toString();
           }
           if(!tvMore.getText().toString().equals("")) {
               orgPassData.description = tvMore.getText().toString();
           }
            orgPassData.communicationtype="02";
           mPresenter.submit(orgPassData);
        }else {
            if(imageFiles==null||imageFiles.size()==0){
                ToastUtil.toastCenter(RobAddVAppointSaleActivity.this,"定位照片不能为空！");
                return;
            }else if(TextUtils.equals(tvLocation.getText().toString(),"")){
                ToastUtil.toastCenter(RobAddVAppointSaleActivity.this,"定位不能为空！");
                return;
            }
            orgPassData.calid = bean.calid;
            orgPassData.communicationtype="03";
            orgPassData.status = "00";
            orgPassData.spotgps=tvLocation.getText().toString();
            orgPassData.description =tvMore.getText().toString();
            mPresenter.submit(orgPassData);
        }

    }
    @Override
    protected RobAddVAppointSaleContract.Presenter initPresenter() {
        return new RobAddVAppointSalePresenter((this));
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_appoint_sale_certification;
    }

    @Override
    public File[] getImgaeFiles() {
        File[] files = new File[imageFiles.size()];
        for (int i = 0; i < imageFiles.size(); i++) {
            files[i] = new File(imageFiles.get(i));
        }
        return files;
    }

    @Override
    public void onSubmitFinish(String errorMessage) {
        EventBus.getDefault().post(new ApproveEvent(RobAddVCertificationActivity.APPROVE_FINISH));
        finish();
    }
}
