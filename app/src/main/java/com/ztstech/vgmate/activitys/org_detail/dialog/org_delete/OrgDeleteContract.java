package com.ztstech.vgmate.activitys.org_detail.dialog.org_delete;

import android.support.annotation.Nullable;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;

/**
 * Created by zhiyuan on 2017/10/24.
 */

interface OrgDeleteContract {

    interface View extends BaseView {


        void onFinish(@Nullable String message);
    }

    interface Presenter extends BasePresenter<View> {

        void deleteOrg(String rbiid, String message);
    }
}
