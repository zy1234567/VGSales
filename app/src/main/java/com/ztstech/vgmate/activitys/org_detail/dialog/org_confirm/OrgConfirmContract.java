package com.ztstech.vgmate.activitys.org_detail.dialog.org_confirm;

import android.support.annotation.Nullable;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;

/**
 * Created by zhiyuan on 2017/10/10.
 */

interface OrgConfirmContract {

    interface View extends BaseView {

        void onFinish(@Nullable String errmsg);
    }

    interface Presenter extends BasePresenter<View> {

        void submit(String rbiid, String oname, String otype, String district, String address,
                    String gps, String contphone);
    }
}
