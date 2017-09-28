package com.ztstech.vgmate.activitys.add_org;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.category_info.CategoryTagsActivity;
import com.ztstech.vgmate.activitys.category_info.CategoryTagsPresenter;
import com.ztstech.vgmate.activitys.gps.GpsActivity;
import com.ztstech.vgmate.activitys.location_select.LocationSelectActivity;
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

    @BindView(R.id.et_org_name)
    EditText etName;
    @BindView(R.id.et_detail_location)
    EditText etDetailLocation;
    @BindView(R.id.et_desc)
    EditText etDesc;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_tag)
    TextView tvTag;
    @BindView(R.id.tv_gps)
    TextView tvGps;

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

        topBar.getRightTextView().setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK != resultCode) {
            return;
        }
        if (REQ_TAG == requestCode) {
            String name = data.getStringExtra(CategoryTagsPresenter.PARAM_NAME);
            String ids = data.getStringExtra(CategoryTagsPresenter.PARAM_ID);
            // TODO: 2017/9/27 缓存id
            tvTag.setText(name);
            tvTag.setTag(ids);
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
        }
    }

    private void submit() {

    }
}
