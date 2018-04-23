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

import com.bumptech.glide.Glide;
import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.manager.MatissePhotoHelper;
import com.ztstech.vgmate.matisse.Matisse;
import com.ztstech.vgmate.matisse.MimeType;
import com.ztstech.vgmate.weigets.TopBar;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ztstech.vgmate.activitys.complete_org_info_v2.subview.pic_video.EditOrgPicVideoActivity.MAX_PIC_COUNT;

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
    @BindView(R.id.tv_more_count)
    TextView tvMoreCount;
    @BindView(R.id.v2)
    View v2;
    @BindView(R.id.ll_reminder)
    LinearLayout llReminder;
    @BindView(R.id.ll_buttom)
    LinearLayout llButtom;
    @BindView(R.id.tv_pass)
    TextView tvPass;
     final  int REQUESTCODE=11;
     File[] files;
    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        initView();
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
    private void addImg(final String imgPath,ImageView img) {
        Glide.with(RobAddVAppointSaleActivity.this).
                load(imgPath).placeholder(R.mipmap.pre_default_image).error(R.mipmap.pre_default_image).into(img);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUESTCODE&&resultCode==RESULT_OK){
            for (int i = 0; i < Matisse.obtainPathResult(data).size(); i++){
                files = new File[1];
                String uri =Matisse.obtainPathResult(data).get(i);
                final File f = new File(uri);
                Glide.with(this).load(f).into(imgVido);
                files[0] = f;
            }
        }else  if(re)
    }
    @OnClick({R.id.rl_ico_gps, R.id.tv_refuse, R.id.tv_pass})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_buttom:
                break;
            case R.id.img_vido:
                choosePicture(1);
                break;
            case R.id.img_location:
                choosePicture(1);
                break;
            default:
                break;
        }
    }
    private  void choosePicture(int maxLength){
        MatissePhotoHelper.selectPhoto(this,
                maxLength , REQUESTCODE,MimeType.ofImage());
    }
    @Override
    protected RobAddVAppointSaleContract.Presenter initPresenter() {
        return new RobAddVAppointSalePresenter((this));
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_appoint_sale_certification;
    }

}
