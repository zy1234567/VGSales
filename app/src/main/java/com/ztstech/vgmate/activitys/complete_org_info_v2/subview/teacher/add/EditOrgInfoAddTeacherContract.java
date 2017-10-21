package com.ztstech.vgmate.activitys.complete_org_info_v2.subview.teacher.add;

import android.support.annotation.Nullable;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.dto.AddTeacherData;

/**
 * Created by zhiyuan on 2017/10/20.
 */

interface EditOrgInfoAddTeacherContract {

    interface View extends BaseView {

        void submitFinish(@Nullable String errmsg);
    }

    interface Presenter extends BasePresenter<View> {

        void submit(AddTeacherData data);
    }
}
