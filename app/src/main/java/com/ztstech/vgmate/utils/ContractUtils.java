package com.ztstech.vgmate.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.widget.Toast;

import com.tbruyelle.rxpermissions.RxPermissions;

import rx.functions.Action1;

/**
 * Created by zhiyuan on 2017/10/25.
 */

public class ContractUtils {

    /**
     * 跳转到联系人
     * @param activity
     * @param requestCode
     */
    public static void toContract(final Activity activity, final int requestCode) {

        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions
                .request(Manifest.permission.READ_CONTACTS)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                            activity.startActivityForResult(i , requestCode);
                        }else {
                            Toast.makeText(activity, "无权限", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /**
     * 读取联系人返回信息
     * @param context
     * @param data
     * @return
     */
    public static ContractUser readContract(Context context, Intent data) {
        ContractUser result = new ContractUser();
        Uri contract = data.getData();
        Cursor c = context.getContentResolver().query(contract, null, null, null, null);
        if (c != null && c.moveToFirst()) {
            String id = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
            result.name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));        //通过Cursor c获得联系人名字
            Cursor c2 = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id, null, null);
            if (c2 != null) {
                c2.moveToFirst();
                result.phone = c2.getString(c2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                c2.close();
            }

        }
        if (c != null) {
            c.close();
        }

        return result;
    }

    public static class ContractUser {
        public String name;
        public String phone;
    }
}
