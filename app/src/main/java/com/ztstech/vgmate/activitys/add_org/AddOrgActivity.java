package com.ztstech.vgmate.activitys.add_org;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.category_info.CategoryTagsActivity;
import com.ztstech.vgmate.activitys.category_info.CategoryTagsPresenter;
import com.ztstech.vgmate.activitys.gps.GpsActivity;
import com.ztstech.vgmate.activitys.location_select.LocationSelectActivity;
import com.ztstech.vgmate.utils.ContractUtils;
import com.ztstech.vgmate.weigets.TopBar;

import butterknife.BindView;

/**
 * 增加机构
 */
public class AddOrgActivity extends MVPActivity<AddOrgContract.Presenter> implements
        AddOrgContract.View, View.OnClickListener {

    /**请求标签*/
    public static final int REQ_TAG = 1;
    /**请求gps信息*/
    public static final int REQ_GPS = 2;
    /**请求地址*/
    public static final int REQ_LOCATION = 3;
    /**请求联系人*/
    public static final int REQ_CONTACT = 4;

    @BindView(R.id.et_org_name)
    EditText etName;
    @BindView(R.id.et_detail_location)
    EditText etDetailLocation;
    @BindView(R.id.et_desc)
    EditText etDesc;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_tag)
    TextView tvTag;
    @BindView(R.id.tv_gps)
    TextView tvGps;
    @BindView(R.id.tv_contact)
    TextView tvContact;

    @BindView(R.id.top_bar)
    TopBar topBar;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_add_org;
    }

    @Override
    protected AddOrgContract.Presenter initPresenter() {
        return new AddOrgPresenter(this);
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        tvLocation.setOnClickListener(this);
        tvGps.setOnClickListener(this);
        tvTag.setOnClickListener(this);
        tvContact.setOnClickListener(this);

        topBar.getRightTextView().setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK != resultCode) {
            return;
        }
        if (REQ_TAG == requestCode) {
            String name = data.getStringExtra(CategoryTagsActivity.PARAM_NAME);
            String ids = data.getStringExtra(CategoryTagsActivity.PARAM_ID);
            tvTag.setText(name);
            tvTag.setTag(ids);
        }else if (REQ_GPS == requestCode) {
            //获取经纬度
            double la = data.getDoubleExtra(GpsActivity.RESULT_LATITUDE, 0);
            double lo = data.getDoubleExtra(GpsActivity.RESULT_LONGITUDE, 0);
            String location = data.getStringExtra(GpsActivity.RESULT_LOCATION);

            tvGps.setText(la + "," + lo);
            etDetailLocation.setText(location);
        }else if (REQ_LOCATION == requestCode) {
            //请求地址
            String locationName = data.getStringExtra(LocationSelectActivity.RESULT_NAME);
            String locationId = data.getStringExtra(LocationSelectActivity.RESULT_CODE);

            tvLocation.setText(locationName);
            tvLocation.setTag(locationId);
        }else if (REQ_CONTACT == requestCode) {
            //得到联系人
            ContractUtils.ContractUser user = ContractUtils.readContract(this, data);
            etPhone.setText(user.phone);

        }
    }

    @Override
    public void onClick(View view) {
        if (view == tvTag) {
            //选择标签
            Intent it = new Intent(this, CategoryTagsActivity.class);
            startActivityForResult(it, REQ_TAG);
        }else if (view == tvGps) {
            //选择gps
            Intent it = new Intent(this, GpsActivity.class);
            startActivityForResult(it, REQ_GPS);
        }else if (view == tvLocation) {
            //选择地址
            Intent it = new Intent(this, LocationSelectActivity.class);
            startActivityForResult(it, REQ_LOCATION);
        }else if (view == topBar.getRightTextView()) {
            //保存
            submit();
        }else if (view == tvContact) {
            //通讯录
            ContractUtils.toContract(this, REQ_CONTACT);
        }
    }

    private void submit() {

    }
}
