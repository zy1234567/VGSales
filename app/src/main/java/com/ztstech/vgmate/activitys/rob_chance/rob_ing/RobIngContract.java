package com.ztstech.vgmate.activitys.rob_chance.rob_ing;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;
import com.ztstech.vgmate.data.dto.OrgRegisterRefuseData;
import com.ztstech.vgmate.data.dto.RefuseOrPassData;

/**
 * Created by dongdong on 2018/4/19.
 */

public interface RobIngContract {
    interface  View extends BaseView{
        void Success();
        void showError(String msg);

    }
    interface  Presenter extends BasePresenter<RobIngContract.View>{
        void refuse0rPassCommit(RefuseOrPassData refuseCalimData);
        void refuseRegisterCommit(OrgRegisterRefuseData orgRegisterRefuseData);
    }

}
