package com.ztstech.vgmate.activitys.qr_code.scan;

import android.support.annotation.Nullable;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;

/**
 * Created by zhiyuan on 2017/11/2.
 */

class QRCodeScanContract {

    interface View extends BaseView {

        void checkUUIDFinish(String uuid, @Nullable String errmsg);
    }

    interface Presenter extends BasePresenter<View> {


        void checkUUID(String uuidm);
    }
}
