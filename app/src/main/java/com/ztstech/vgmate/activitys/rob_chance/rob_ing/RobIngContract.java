package com.ztstech.vgmate.activitys.rob_chance.rob_ing;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.dto.OrgPassData;
import com.ztstech.vgmate.data.dto.RefuseOrPassData;

/**
 * Created by dongdong on 2018/4/19.
 */

public interface RobIngContract {
    interface  View extends BaseView{
        void Success();
        void showError(String msg);
        void onSubmitFinish(String errorMessage);
    }
    interface  Presenter extends BasePresenter<RobIngContract.View>{
        void refuse0rPassCommit(RefuseOrPassData refuseCalimData,int type);
        void refuseRegisterCommit(RefuseOrPassData orgRegisterRefuseData, int  type);
        /**
         * 路人登记（定位认证）
         */
        void locationCertificationCommit(OrgPassData orgPassData);
    }

}
