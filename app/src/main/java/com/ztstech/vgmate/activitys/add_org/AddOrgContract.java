package com.ztstech.vgmate.activitys.add_org;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.dto.AddOrgData;

/**
 * Created by zhiyuan on 2017/9/27.
 */
interface AddOrgContract {

    interface View extends BaseView {
        void onSubmitFinish(String msg);
    }

    interface Presenter extends BasePresenter<View> {
        void commit(AddOrgData data);
    }
}
