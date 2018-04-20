package com.ztstech.vgmate.activitys.add_certification;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.dto.AddOrgData;
import com.ztstech.vgmate.data.dto.AddVData;

/**
 * Created by Administrator on 2018/4/20.
 */

interface AddVContract {
    interface View extends BaseView {
        void onSubmitFinish(String msg);
    }

    interface Presenter extends BasePresenter<AddVContract.View> {
        void commit(AddVData data);
    }
}
