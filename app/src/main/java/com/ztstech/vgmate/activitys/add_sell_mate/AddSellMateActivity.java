package com.ztstech.vgmate.activitys.add_sell_mate;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ztstech.vgmate.R;
import com.ztstech.vgmate.activitys.MVPActivity;
import com.ztstech.vgmate.activitys.location_select.LocationSelectActivity;
import com.ztstech.vgmate.data.dto.AddSellMateData;

import butterknife.BindView;

/**
 * 添加销售伙伴
 */
public class AddSellMateActivity extends MVPActivity<AddSellMateContract.Presenter> implements
        AddSellMateContract.View, View.OnClickListener {

    /**请求选择地址*/
    public static final int REQ_LOCATION = 1;
    /**请求联系人*/
    public static final int REQ_CONTACT = 2;

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_id)
    EditText etId;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.cb_send_code)
    CheckBox cbSendCode;
    @BindView(R.id.btn_submit)
    Button btSubmit;
    @BindView(R.id.tv_contact)
    TextView tvContract;

    /**页面数据*/
    private AddSellMateData addSellMateData = new AddSellMateData();

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_add_sell_mate;
    }


    @Override
    protected AddSellMateContract.Presenter initPresenter() {
        return new AddSellMatePresenter(this);
    }

    @Override
    protected void onViewBindFinish() {
        super.onViewBindFinish();
        tvLocation.setOnClickListener(this);
        btSubmit.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_LOCATION && resultCode == RESULT_OK) {
            //得到地址
            addSellMateData.wprovince = data.getStringExtra(LocationSelectActivity.RESULT_P);
            addSellMateData.wcity = data.getStringExtra(LocationSelectActivity.RESULT_C);
            addSellMateData.wdistrict = data.getStringExtra(LocationSelectActivity.RESULT_A);

            tvLocation.setText(data.getStringExtra(LocationSelectActivity.RESULT_NAME));

        }else if (requestCode == REQ_CONTACT && resultCode == RESULT_OK) {
            //得到联系人
            Uri contract = data.getData();
            Cursor c = this.getContentResolver().query(contract, null, null, null, null);
            if (c != null && c.moveToFirst()) {
                String suspect = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));        //通过Cursor c获得联系人名字
                String id = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));

                Cursor c2 = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id, null, null);
                if (c2 != null) {
                    c2.moveToFirst();
                    String phonenum = c2.getString(c2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    etPhone.setText(phonenum);
                    if (TextUtils.isEmpty(etName.getText().toString())) {
                        etName.setText(suspect);
                    }
                    addSellMateData.phone = phonenum;
                    c2.close();
                }

            }
            if (c != null) {
                c.close();
            }
        }

    }

    @Override
    public void onClick(View view) {
        if (view == tvLocation) {
            //去选择地址
            startActivityForResult(new Intent(this, LocationSelectActivity.class), REQ_LOCATION);
        }else if (view == btSubmit) {
            checkToSubmit();
        }else if (view == tvContract) {
            //打开联系人
            Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            startActivityForResult(i , REQ_CONTACT);
        }
    }


    /**
     * 检测提交
     */
    private void checkToSubmit() {

        addSellMateData.phone = etPhone.getText().toString();
        addSellMateData.uname = etName.getText().toString();
        addSellMateData.sendCode = cbSendCode.isChecked();
        addSellMateData.did = etId.getText().toString();

        if (TextUtils.isEmpty(addSellMateData.wprovince)) {
            Toast.makeText(this, "请选择地址", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(addSellMateData.did)) {
            Toast.makeText(this, "请填写身份证号", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(addSellMateData.phone)) {
            Toast.makeText(this, "请填写手机号码", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(addSellMateData.uname)) {
            Toast.makeText(this, "请填写姓名", Toast.LENGTH_SHORT).show();
            return;
        }



        mPresenter.submit(addSellMateData);
    }

    @Override
    public void onSubmitFinish(String errorMessage) {

    }
}
