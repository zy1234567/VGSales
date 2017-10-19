package com.ztstech.vgmate.activitys.complete_org_info_v2.subview.teacher.list;

import android.support.annotation.Nullable;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.beans.TeacherListBean;

import java.util.List;

/**
 * Created by zhiyuan on 2017/10/19.
 */

interface EditOrgInfoTeacherContract {

    interface View extends BaseView{

        void loadTeacherFinish(List<TeacherListBean.ListBean> items, @Nullable String errmsg);

        void appendTeacherFinish(List<TeacherListBean.ListBean> items, @Nullable String errmsg);


    }

    interface Presenter extends BasePresenter<View> {

        void loadTeachers(int rbiid);

        void appendTeachers(int rbiid);
    }
}
