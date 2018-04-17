package com.ztstech.vgmate.activitys.org_follow.claim_org;

import com.ztstech.vgmate.activitys.BasePresenter;
import com.ztstech.vgmate.activitys.BaseView;

/**
 * Created by smm on 2017/12/6.
 */

public interface ClaimOrgDetailContact {

    interface View extends BaseView{
        void onApproveSuccess();
        void showError(String msg);
    }

    interface Presenter extends BasePresenter<View>{
        void approveOrg(String rbiid,String calid,String identtype,String status,String type,String yeorno,String testorg,String oname);
    }

}
